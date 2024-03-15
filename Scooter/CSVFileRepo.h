
#ifndef SCOOTERS_CSVFILEREPO_H
#define SCOOTERS_CSVFILEREPO_H

#include"Domain.h"
#include "Repository.h"
#include<string>
using namespace std;

using namespace repository;

class CSVFileRepository : public ScooterRepository {
private:
    string CSV; // Name of the CSV file


public:
    CSVFileRepository();

    void read_csv();
    void write_csv();

    void add_scooter(const Scooter& scooter_to_be_added);

    bool delete_scooter(const Scooter& scooter_to_be_deleted);

    void modify_scooter(const Scooter &scooter_to_be_modified);

    Scooter find_by_id(const string &id);


    ~CSVFileRepository();
};


#endif //SCOOTERS_CSVFILEREPO_H
