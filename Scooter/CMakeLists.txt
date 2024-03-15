cmake_minimum_required(VERSION 3.27)
project(Scooter)

set(CMAKE_CXX_STANDARD 17)

add_executable(Scooter
        UI.cpp
        InMemoryRepo.cpp
        Domain.cpp
        CSVFileRepo.cpp
        Controller.cpp
        app.cpp)
