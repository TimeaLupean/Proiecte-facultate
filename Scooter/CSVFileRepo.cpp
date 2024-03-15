#include "Domain.h"
#include"Repository.h"
#include"CSVFileRepo.h"
#include<fstream>
#include <algorithm>

using namespace std;
using namespace repository;

CSVFileRepository::CSVFileRepository() {
    CSV = "fisier.txt";
    read_csv();
}

void CSVFileRepository::read_csv() {
    ifstream file(CSV);
    string line;
    string aux = "";
    string new_id, new_model, new_date, new_last_seen;
    double new_km;
    State new_state;
    while (getline(file, line)) {

        int cct = 0;
        for (int i = 0; i < line.size(); i++) {
            if (line[i] == ',') {
                cct++;
                switch (cct) {
                    case 1: //ist  id
                        new_id = aux;
                        break;
                    case 2: //ist model
                        new_model = aux;
                        break;
                    case 3: //ist date
                        new_date = aux;
                        break;
                    case 4: //ist km
                        new_km = stoi(aux);
                        break;
                    case 5: //ist last seen
                        new_last_seen = aux;
                        break;
                }
                aux = "";
            } else {
                if (line[i] == '\n') {
                    //new_state = aux;
                    if (aux == "parked") new_state = parked;
                    else if (aux == "reserved") new_state = reserved;
                    else if (aux == "in_use") new_state = in_use;
                    else if (aux == "waiting") new_state = waiting;
                    else if (aux == "out_of_order") new_state = out_of_order;
                } else aux += line[i];
            }
        }
        Scooter new_scoot(new_id, new_model, new_date, new_km, new_last_seen, new_state);
        scooters.push_back(new_scoot);
    }
    file.close();
}

void CSVFileRepository::write_csv() {
    ofstream file(CSV);
    if (file.is_open()) {
        for (auto &scooter: scooters) {
            file << scooter.get_id() << ','
                 << scooter.get_model() << ','
                 << scooter.get_commissioning_date() << ','
                 << scooter.get_km() << ','
                 << scooter.get_last_seen() << ','
                 << scooter.get_state() << '\n';
        }
        file.close();
    } else {
        throw exception();
    }
}

void CSVFileRepository::add_scooter(const Scooter &scooter_to_be_added) {
    scooters.push_back(scooter_to_be_added);
}

bool CSVFileRepository::delete_scooter(const Scooter &scooter_to_be_deleted) {
    for (auto it = scooters.begin(); it < scooters.end(); it++) {
        if ((*it).get_id() == scooter_to_be_deleted.get_id()) {
            scooters.erase(it);
            return true;
        }
    }
    return false;
}

void CSVFileRepository::modify_scooter(const Scooter &scooter_to_be_modified) {
    delete_scooter(find_by_id(scooter_to_be_modified.get_id()));
    add_scooter(scooter_to_be_modified);
}

Scooter CSVFileRepository::find_by_id(const string &id){
    for(int i=0;i<scooters.size();i++)
        if(scooters[i].get_id()==id)
            return reinterpret_cast<const Scooter &>(scooters[i]);
}



CSVFileRepository::~CSVFileRepository() {
    write_csv();
}