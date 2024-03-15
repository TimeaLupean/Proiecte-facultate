#include "Domain.h"
using namespace domain;



Scooter::Scooter(string id, string model = "", string commissioning_date = "", double km = 0.0, string last_seen = "", State state = parked) {
    this->id = id;
    this->model = model;
    this->commissioning_date = commissioning_date;
    this->km = km;
    this->last_seen = last_seen;
    this->state = state;
}

string Scooter::get_id() const {
    return id;
}

string Scooter::get_model() const {
    return model;
}

string Scooter::get_commissioning_date() const {
    return commissioning_date;
}

double Scooter::get_km() const {
    return km;
}

string Scooter::get_last_seen() const {
    return last_seen;
}

State Scooter::get_state() const {
    return state;
}

void Scooter::set_id(string id) {
    this->id = id;
}

void Scooter::set_model(string model) {
    this->model = model;
}

void Scooter::set_commissioning_date(string commissioning_date) {
    this->commissioning_date = commissioning_date;
}

void Scooter::set_km(double km) {
    this->km = km;
}

void Scooter::set_last_seen(string last_seen) {
    this->last_seen = last_seen;
}

void Scooter::set_state(State state) {
    this->state = state;
}

Scooter::~Scooter() {}