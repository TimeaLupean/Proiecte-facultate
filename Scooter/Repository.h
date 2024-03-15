#include "Domain.h"
#include <vector>

#pragma once

#include<memory>

using namespace domain;

namespace repository {
    class ScooterRepository {

    protected:
        vector <Scooter> scooters;
        vector <shared_ptr<Scooter>> reservedScooters;

    public:

        virtual void add_scooter(const Scooter &scooter_to_be_added) = 0;

        virtual bool delete_scooter(const Scooter &scooter_to_be_deleted) = 0;

        virtual void modify_scooter(const Scooter &scooter_to_be_modified) = 0;

        vector <Scooter> get_all() {
            return scooters;
        };

        virtual Scooter find_by_id(const string &id) = 0;

    };
}