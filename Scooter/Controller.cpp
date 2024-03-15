#include <algorithm>
#include "Controller.h"
#include "InMemoryRepo.h"

using namespace controller;

Controller::Controller(shared_ptr<ScooterRepository> repo) {
    repository = repo;
}


void Controller::add_scooter(string id, string model, string commissioning_date, double km, string last_seen, State state) {
    Scooter some_scooter(id, model, commissioning_date, km, last_seen, state);
    repository->add_scooter(some_scooter);
}


bool Controller::delete_scooter(string id, string model, string commissioning_date, double km, string last_seen,
                                State state) {
    Scooter some_scooter(id, model, commissioning_date, km, last_seen, state);
    repository->delete_scooter(some_scooter);
}

vector<Scooter> Controller::SearchScooter(int choice, const string &commissioning_date, double km) {
    vector<Scooter> SomeScooters;


    switch (choice) {
        case 1:
            SomeScooters = search_comm(commissioning_date);
            break;

        case 2:
            SomeScooters = search_km(double(km));
            break;

        case 3:
            SomeScooters = search_loc(commissioning_date);
            break;

        default:
            return SomeScooters;
    }

    return SomeScooters;
}


vector<Scooter> Controller::display_scooters_sorted_by_commissioning_date() {
    vector<Scooter> THEscooters = repository->get_all();
    std::sort(THEscooters.begin(), THEscooters.end(), srt_by_date);
    return THEscooters;
}

vector<Scooter> Controller::display_scooters_by_last_seen(const string &last_seen) {
    vector<Scooter> THEscooters = repository->get_all();
    std::sort(THEscooters.begin(), THEscooters.end(), srt_by_place);
    return THEscooters;
}

void Controller::update_scooter(int choice, const string &id, string change, double kms, State state) {
    Scooter change_it = repository->find_by_id(id);
    switch (choice) {
        case 1:
            change_it.set_commissioning_date(change);
            break;

        case 2:
            change_it.set_km(kms);
            break;

        case 3:
            change_it.set_last_seen(change);
            break;
        case 4:
            change_it.set_state(state);
            break;
    }
    repository->modify_scooter(change_it);
}

vector<Scooter> Controller::display_reserved_scooters() {
    vector<Scooter> THEscooters = repository->get_all();
    vector<Scooter> reserved_vect;
    for (int i = 0; i < THEscooters.size(); i++)
        if (THEscooters[i].get_state() == reserved) {
            reserved_vect.push_back(THEscooters[i]);
        }
    return reserved_vect;
}

bool Controller::srt_by_date(const Scooter &s1, const Scooter &s2) {
    return s1.get_commissioning_date() > s2.get_commissioning_date();
}

bool Controller::srt_by_place(const Scooter &s1, const Scooter &s2) {
    return s1.get_last_seen() > s2.get_last_seen();
}

vector<Scooter> Controller::search_comm(string comm_date) {
    vector<Scooter> THEscooters = repository->get_all();
    vector<Scooter> found;
    for (int i = 0; i < THEscooters.size(); i++)
        if (THEscooters[i].get_commissioning_date().find(comm_date) != string::npos)
            found.push_back(THEscooters[i]);
    return found;
}

vector<Scooter> Controller::search_km(double km) {
    vector<Scooter> THEscooters = repository->get_all();
    vector<Scooter> found;
    for (int i = 0; i < THEscooters.size(); i++)
        if (THEscooters[i].get_km() == km)
            found.push_back(THEscooters[i]);
    return found;
}

vector<Scooter> Controller::search_loc(string location) {
    vector<Scooter> THEscooters = repository->get_all();
    vector<Scooter> found;
    for (int i = 0; i < THEscooters.size(); i++)
        if (THEscooters[i].get_last_seen().find(location) != string::npos)
            found.push_back(THEscooters[i]);
    return found;
}
