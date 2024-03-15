#include <iostream>
#include "UI.h"

using namespace ui;

void Run() {
    // declaration of the variable, which will hold the input
    cout<<"~\n";
    cout << "How would you like the data to be stored?\n\t1. In memory\n\t2. In a CSV file\n";
    char how;
    cin>>how;

    shared_ptr<ScooterRepository> repo;


    if(how=='1'){
        shared_ptr<InMemoryRepository> repo;

    }
    else if(how=='2') {
        shared_ptr<CSVFileRepository> repo;
    }
    else { cerr << "Invalid input" << endl; return;}

    shared_ptr<Controller>control = make_shared<Controller> (repo);
    UI ui1(control);


    cout<<"~~~\n";
    cout << "Who is using the app?\n\t1. Manager\n\t2. User\n\t0. Exit\n";
    char who;
    cout<<"~~~\n";
    do {
        cin >> who;
        switch (who) {
            case '1': {
                ui1.MenuManager();
                break;
            }
            case '2': {
                ui1.MenuUser();
                break;
            }
        }
    }
    while (who != 0);
    return;
}

int main() {

    Run();

    return 0;
}