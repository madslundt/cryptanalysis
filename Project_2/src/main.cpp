#include <math.h>
#include <string>
#include <stdlib.h>
#include <iostream>
#include "Rainbowtables.h"
#include "attack.h"
#include "variables.h"

using namespace std;

const std::string hex_digits    = "0123456789ABCDEF";
const int BIT_SIZE              = 28;
const unsigned int MAX          = 1000;
const char* SAVE_FILE           = "rainbowtable.csv";
const unsigned int MAX_HEX      = (int) pow(2.0, (double) BIT_SIZE);
const std::string s             = "S123456";
const std::string u             = "THISKEY";

int main()
{
    while (true) {
        char c1;
        unsigned int chains, chain_length, length;
        std::tr1::unordered_map<string, string> start_points;
        std::tr1::unordered_map<string, string> end_points;
        cout << "\n1: Precomputation phase (Generate Rainbow table)" << endl;
        cout << "2: Online phase" << endl;
        cout << "!: Exit program" << endl;
        cout << "Please enter a value: ";
        cin >> c1;


        switch(c1) {
            case '1':
                char c2;
                cout << "The precomputation phase will take a while. Confirm (y/n)?" << endl;
                cin >> c2;
                if(c2 == 'y'){
                    chains = pow(2.0,18.0);
                    chain_length = (int) pow(2.0,10.0);
                    generateRainbowtables(chains, chain_length, start_points, end_points);
                    saveRainbowtable(start_points);
                    length = start_points.size();
                    cout << "Generated " << length << endl;
                }
                break;
            case '2':
                loadRainbowtable(start_points);
                for (auto it : start_points) {
                    cout << it.first << " => " << it.second << endl;
                }
                cout << findS() << endl;
                return 0;
            case '!':
                return 0;
            default:
                cout << "The entered value is not a valid input - try again." << endl;
        }
    }
    return 0;
}


