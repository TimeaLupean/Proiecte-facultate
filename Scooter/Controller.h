#include "Repository.h"
#include "CSVFileRepo.h"
#include "InMemoryRepo.h"
#include <memory>
#pragma once

using namespace repository;

namespace controller {
    class Controller {

    private:
        shared_ptr<ScooterRepository> repository;

        static bool srt_by_date(const Scooter &s1, const Scooter &s2);
        static bool srt_by_place(const Scooter &s1, const Scooter &s2);

        vector<Scooter> search_comm(string comm_date);
        vector<Scooter> search_km(double km);
        vector<Scooter> search_loc(string location);

    public:

        // constructor
        Controller(shared_ptr<ScooterRepository> repo);


        void add_scooter(string id, string model, string commissioning_date, double km, string last_seen, State state);


        bool delete_scooter(string id, string model, string commissioning_date, double km, string last_seen, State state);

        vector<Scooter> SearchScooter(int choice, const string &commissioning_date = "", double km = -1);

        vector<Scooter> display_scooters_sorted_by_commissioning_date();

        vector<Scooter> display_scooters_by_last_seen(const string &last_seen);

        vector<Scooter> display_reserved_scooters();


        void update_scooter(int choice, const string &id, string change = "", double kms = 0.0, State state=parked);
    };
}