#include <string>
#pragma once
using namespace std;

enum State {parked=0,reserved=1,in_use=2,waiting=3,out_of_order=4};

namespace domain {

    class Scooter {

    private:
        string id;                  // id is composed out of 3 characters
        string model;               // model
        string commissioning_date;  // date since it started being used
        double km;                  // km amount
        string last_seen;           // string, which indicates where the scooter has been seen last
        State state;               // state, which indicates wheter the scooter is in use, parked, on hold, etc.

    public:

        // constructor
        Scooter(string id, string model, string commissioning_date, double km, string last_seen, State state);

        // GETTERS for each attribute of the class

        string get_id() const;

        string get_model() const;

        string get_commissioning_date() const;

        double get_km() const;

        string get_last_seen() const;

        State get_state() const;

        // SETTERS for each attribute of the class

        void set_id(string id);

        void set_model(string model);

        void set_commissioning_date(string commissioning_date);

        void set_km(double km);

        void set_last_seen(string last_seen);

        void set_state(State state);

        // destructor
        ~Scooter();

        bool operator==(const Scooter &other) const{
            return this->get_id()==other.get_id();
        }
    };

}