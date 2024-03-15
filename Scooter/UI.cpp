#include <iostream>
#include "UI.h"
#include "CSVFileRepo.h"

using namespace std;
using namespace ui;

UI::UI(shared_ptr<Controller> contr) {
    controller = contr;
}

void UI::MenuManager() {
    cout << "Scooter Shop [Manager view]:\n";
    cout << "1. Add a scooter\n";
    cout << "2. Delete a scooter\n";
    cout << "3. Update a scooter\n";
    cout << "4. Search for scooter\n";
    cout << "5. Display scooters by age \n";
    cout << "0. End\n";

    char choice;
    do {
        cin >> choice;
        cout<<"-----------------------\n";
        switch (choice) {
            case '1': {
                add_scoot();
                break;
            }
            case '2': {
                del_scoot();
                break;
            }
            case '3': {
                update_scoot();
                break;
            }
            case '4': {
                search_scoot_nr();
                break;
            }
            case '5': {
                display();
                break;
            }
            case '0': {
                cout << "See you soon!\n";
                break;
            }
            default:
                cout << "The option you are trying to access is not valid!\n";
        }
        cout << endl;
        cout<<"---\n";
        cout<<"Now what?"<<endl;
        // exit condition
    } while (choice != '0');
}

void UI::MenuUser() {
    cout << "Scooter Shop [User view]:\n";
    cout << "1. Search for scooter\n";
    cout << "2. Reserve scooter\n";
    cout << "3. Use scooter \n";
    cout << "4. Reserve scooter \n";
    cout << "0. End\n";

    char choice;
    cout<<"-----------------------\n";
    do {
        cin >> choice;
        switch (choice) {
            case '1': {
                search_scoot_nr();
                break;
            }
            case '2': {
                reserve();
                break;
            }
            case '3': {
                use();
                break;
            }
            case '4': {
                update_scoot();
                break;
            }
            case '0': {
                cout << "See you soon!\n";
                break;
            }
            default:
                cout << "The option you are trying to access is not valid!\n";
        }
        cout << endl;
        cout<<"---\n";
        cout<<"Now what?"<<endl;
    } while (choice != '0');
}

void UI::add_scoot() {
    string id, model,comm_date, last_seen;
    double km;
    cout<<"ID: ";
    cin>>id;
    cout<<"Model: ";
    cin>>model;
    cout<<"Commisioning Date: ";
    cin>>comm_date;
    cout<<"Last seen:";
    cin>>last_seen;
    cout<<"Km: ";
    cin>>km;
    cout<<"State: \n[parked/reserved/in_use/waiting/out_of_order]";

    int stateInput;
    cin >> stateInput;

    State state;
    switch (stateInput) {
        case 0:
            state = parked;
            break;
        case 1:
            state = reserved;
            break;
        case 2:
            state = in_use;
            break;
        case 3:
            state = waiting;
            break;
        case 4:
            state = out_of_order;
            break;
        default:
            // Handle invalid input or set a default state
            state = parked; // Assuming parked is the default state
            break;
    }


    controller->add_scooter(id,model,comm_date,km,last_seen,state);
}

void UI::del_scoot() {
    string id, model,comm_date, last_seen;
    double km;
    bool deleted;
    cout<<"ID: ";
    cin>>id;
    cout<<"Model: ";
    cin>>model;
    cout<<"Commissioning Date: ";
    cin>>comm_date;
    cout<<"Last seen:";
    cin>>last_seen;
    cout<<"Km: ";
    cin>>km;
    cout<<"State: \n[parked/reserved/in_use/waiting/out_of_order]";


    int stateInput;
    cin >> stateInput;

    State state;
    switch (stateInput) {
        case 0:
            state = parked;
            break;
        case 1:
            state = reserved;
            break;
        case 2:
            state = in_use;
            break;
        case 3:
            state = waiting;
            break;
        case 4:
            state = out_of_order;
            break;
        default:
            // Handle invalid input or set a default state
            state = parked; // Assuming parked is the default state
            break;
    }

    deleted=controller->delete_scooter(id,model,comm_date,km,last_seen,state);
    if(deleted) cout<<"Scooter deleted successfully\n";
    else cout<<"No such scooter found!\n";
    return;
}

void UI::update_scoot() {
    string id, the_change; int option;
    cout<<"ID of the scooter you would like to update: ";
    cin>>id;
    cout<<"What would you like to change?\n\t1. Commissioning date\n\t2. Km\n\t3. Last seen\n\t4. State\nchoice (number): ";
    cin>>option;
    cout<<"To what should it be changed?";
    cin>>the_change;
    if(option<1 or option>4) { cout <<"Invalid input!"; return;}
    controller->update_scooter(option,id,the_change);
}

///Search for scooter by age or km or location
void UI::search_scoot_nr() {
    vector<Scooter>  to_display;
    int age_km;
    cout<<"Are you looking for a specific commissioning date or km-count?\n";
    cout<<"\t1. commissioning date\n\t2. km-count\n\t3. location";
    cin>>age_km;
    cout<<"To look for:";
    switch(age_km) {
        case 1: {
            string look_for;
            cin >> look_for;
            to_display=controller->SearchScooter(age_km, look_for);
            break;
        }

        case 2: {
            double look_for;
            cin >> look_for;
            to_display = controller->SearchScooter(age_km, "", look_for);
            break;
        }

        case 3: {
            string look_for;
            cin >> look_for;
            to_display = controller->SearchScooter(age_km, look_for);
            break;
        }

        default:
            cout<<"Invalid answer\n";
            return;

    }
    for(int i=0;i<to_display.size();i++)
        cout<<to_display[i].get_id()<<"\t"<<to_display[i].get_model()<<"\t"<<to_display[i].get_commissioning_date()<<"\t"<<to_display[i].get_km()<<"\t"<<to_display[i].get_last_seen()<<'\n';
}

///Ein Manager möchte alle Elektroscooter nach Alter aufsteigend sortiert sehen.
void UI::display() {
    vector<Scooter>  to_display;
    to_display=controller->display_scooters_sorted_by_commissioning_date();
    for(int i=0;i<to_display.size();i++)
        cout<<to_display[i].get_id()<<"\t"<<to_display[i].get_model()<<"\t"<<to_display[i].get_commissioning_date()<<"\t"<<to_display[i].get_km()<<"\t"<<to_display[i].get_last_seen()<<"\t"<<to_display[i].get_state() <<'\n';
}


void UI::reserve() {
    string id,choice;
    cout<<"ID of the scooter you would like to update: ";
    cin>>id;
    controller->update_scooter(4,id,"reserved");
    cout << "If you would like to reserve another scooter please type '1', else type '2'"<< endl;
    cin>>choice;
    if(choice == "1")
        reserve();
}

void UI::use() {
    string id;
    cout<<"ID of the scooter you would like to update: ";
    cin>>id;
    controller->update_scooter(4,id,"in_use");
}
//
//void UI::reserve_more(){
//    cout<<"End the reading with 0!\n";
//    string id;
//    while(id!="0"){
//        cin>>id;
//    }
//}