#pragma once

#include "Repository.h"

using namespace repository;

class InMemoryRepository : public ScooterRepository {
private:
    //vector<shared_ptr<Scooter>> scooters;
public:
    InMemoryRepository();

    void add_scooter(const Scooter &scooter_to_be_added);

    bool delete_scooter(const Scooter &scooter_to_be_deleted) override;

    void modify_scooter(const Scooter &scooter_to_be_modified);

    Scooter find_by_id(const string &id);

};