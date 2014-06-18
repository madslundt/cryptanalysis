#include <math.h>
#include <string>
#include <stdlib.h>
#include <iostream>
#include "Rainbowtables.h"
#include "Attack.h"

using namespace std;

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
                return 0;

            case '!':
                return 0;
            default:
                cout << "The entered value is not a valid input - try again." << endl;
        }
    }
    return 0;
}


