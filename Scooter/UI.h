#pragma once
#include "Controller.h"
#include <memory>

using namespace controller;

namespace ui {
    class UI {
    public:
        UI(shared_ptr<Controller> contr);

        void MenuUser();

        void MenuManager();

    private:
        shared_ptr <Controller> controller;

        void add_scoot();

        void del_scoot();

        void update_scoot();

        void search_scoot_nr();

        void display();

        void reserve();

        void use();

        //void reserve_more();
    };
}

