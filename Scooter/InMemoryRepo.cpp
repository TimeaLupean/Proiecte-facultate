#include "InMemoryRepo.h"
#include "Domain.h"
#include<vector>
#include<algorithm>

using namespace std;
using namespace repository;


InMemoryRepository::InMemoryRepository() {

    Scooter s1("123", "Lime", "2022.03.12", 99, "Str.Donath", parked);
    Scooter s2("124", "Bolt", "2021.06.12", 200, "Str.13Septembrie", parked);
    Scooter s3("125", "Lime", "2020.12.05", 156, "Str.Parang", in_use);
    Scooter s4("126", "Lime", "2023.06.07", 341, "Str.Cojocnei", reserved);
    Scooter s5("127", "Bolt", "2013.12.03", 599, "Str.Donath", in_use);
    Scooter s6("128", "Lime", "2002.12.03", 400, "Str.Ploiesti", in_use);
    Scooter s7("129", "Bolt", "2020.09.01", 199, "Str.DealulFurcilor", reserved);
    Scooter s8("130", "Lime", "2021.07.06", 201, "Str.13Decembrie", out_of_order);
    Scooter s9("131", "Bolt", "2022.02.04", 199, "Str.Govora", waiting);
    Scooter s10("132", "Lime", "2023.03.10", 204, "Str.Brukenthal", in_use);

    scooters.push_back(s1);
    scooters.push_back(s2);
    scooters.push_back(s3);
    scooters.push_back(s4);
    scooters.push_back(s5);
    scooters.push_back(s6);
    scooters.push_back(s7);
    scooters.push_back(s8);
    scooters.push_back(s9);
    scooters.push_back(s10);

}

void InMemoryRepository::add_scooter(const Scooter &scooter_to_be_added) {
    scooters.push_back(scooter_to_be_added);
}


bool InMemoryRepository::delete_scooter(const Scooter &scooter_to_be_deleted) {
    for (auto it = scooters.begin(); it < scooters.end(); it++) {
        if ((*it).get_id() == scooter_to_be_deleted.get_id()) {
            scooters.erase(it);
            return true;
        }
    }
    return false;
}

void InMemoryRepository::modify_scooter(const Scooter &scooter_to_be_modified) {
    delete_scooter(find_by_id(scooter_to_be_modified.get_id()));
    add_scooter(scooter_to_be_modified);
}

Scooter InMemoryRepository::find_by_id(const string &id){
    for(int i=0;i<scooters.size();i++)
        if(scooters[i].get_id()==id)
            return reinterpret_cast<const Scooter &>(scooters[i]);
}


