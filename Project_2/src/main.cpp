#include <math.h>
#include <string>
#include <stdlib.h>
#include <iostream>
#include <unordered_map>
#include <ctime>
#include <vector>
#include "Rainbowtables.h"
#include "attack.h"
#include "variables.h"
#include <algorithm>

using namespace std;

const unsigned int CHAINS       = (int) pow(2.0,18.0);
const unsigned int CHAIN_LENGTH = (int) pow(2.0,10.0);
const std::string hex_digits    = "0123456789abcdef";
const int BIT_SIZE              = 28;
const unsigned int MAX          = 1000;
const char* SAVE_FILE           = "rainbowtable.csv";
const unsigned int MAX_HEX      = (int) pow(2.0, (double) BIT_SIZE);
std::string u             = "THISKEY";

bool unique_func(string s1, string s2)
{
    return (s1 == s2);
}



int main()
{
    srand (time (0));  // Needs to be done so the rand() not will give the same over and over again.
    char c1;
    string s, r, r2;
    string old_u;
    old_u = u;
    int i;
    std::vector<string>::iterator it;
    std::vector<string>::const_iterator l, j;
    std::tr1::unordered_map<string, string> start_points;
    vector<string> possible_keys;
    while (true) {
        cout << "\n1: Precomputation phase (Generate Rainbow table)" << endl;
        cout << "2: Online phase" << endl;
        cout << "3: Test possible keys" << endl;
        cout << "!: Exit program" << endl;
        cout << "Please enter a value: ";
        cin >> c1;

        //the user switch case, between precomputation- and online phase
        switch(c1) {
            case '1':
                char c2;
                cout << "The precomputation phase will take a while. Confirm (y/n)?" << endl;
                cin >> c2;
                if (c2 == 'y') {
                    generateRainbowtables(start_points);
                    saveRainbowtable(start_points);
                    //length = start_points.size();
                    //cout << "Generated " << length << endl;
                }
                break;
            case '2':
                for(i = 0 ; i < 100; i++){
                cout << "TURN NUMBER: " << i << endl;
                loadRainbowtable(start_points);
                //length = start_points.size();
                //cout << "No. of keys in rainbowtable:\t" << length << endl;
                cout << "\nFind matching end points..." << endl;
                s = randomHex(); // Setting a random s to be found (unknown).
                cout << "(Privat key " << s << ")" << endl;
                //for loop trying to find s with 10 different s keys
                //for (i = 0; i < 10; ++i) {
                    //cout << "TURN NUMBER: " << i << endl;
                    possible_keys = findS(start_points, s); //finding all the possible s values
                    //printing the s values found if not empty
                    if (!possible_keys.empty()) {
                        cout << "Printing possible s values:" << endl;
                        for (j = possible_keys.begin(); j != possible_keys.end(); ++j) {
                        cout << ">>> A possible key is: " << *j << endl;
                        }
                    } else {
                        cout << "No key found." << endl;
                    }
                //}
                //return 0;
                //break;
            case '3':
                if(possible_keys.empty())
                {
                    cout << "No key collection found" << endl;
                    break;
                }
                u = "NEXTTRY"; //create a new u
                r = md5_redux(s); //new response from the car key UNKNOWN!!!
                cout << "Make check for new u" << endl;
                for (l = possible_keys.begin(); l != possible_keys.end(); ++l) {
                    r2 = md5_redux(*l);
                    if(r == r2)
                    {
                        cout << "Checks key: " << *l << "\t\t=> SUCCESS" << endl;
                    }
                    else
                    {
                        cout << "Checks key: " << *l <<"\t\t=> FAILURE" << endl;
                    }
                }
                u = old_u;}
                //break;
            case '!':
                return 0;
            default:
                cout << "The entered value is not a valid input - try again." << endl;
        }
    }
    return 0;
}


