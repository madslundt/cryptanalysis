#include <iostream>
#include <string>
#include <tr1/unordered_map>
#include <vector>
#include "Rainbowtables.h"
#include "variables.h"

using namespace std;

/**
 * @brief findS
 * @param points All the start- and end points.
 * @return string with the key (empty string = no key)
 */
string findS(std::tr1::unordered_map<string, string> &points) {
    size_t i, j;
    string r, s, s2, carKey;
    vector<string> succ;
    s = randomHex(); // Setting a random s to be found (unknown).
    carKey = f(md5_redux(s), 0); // Running the function and md5 the s.
    succ.insert(succ.begin(), carKey); // Insert into successors.

    // Calculating the next successors.
    for (i = 0; i < CHAIN_LENGTH; ++i) {
        succ.insert(succ.end(), f(md5_redux(succ[i]), i+1));
    }
    cout << "s\t=\t" << s << endl;
    cout << "Car key: " << carKey << endl;

    // Going through all the start- and endpoints.
    for (auto it : points) {
        // Checks if the endpoint is in one of the successors.
        if (std::find(succ.begin(), succ.end(), it.second) != succ.end()) {
            cout << "Found something.\t" << it.first << " => " << it.second << endl;

            // Setting start point and running through the start point to see if carKey is in the chain.
            s2 = it.first;
            for (i = 0; i < CHAIN_LENGTH; ++i) {
                r = f(md5_redux(s2), i);
                if (r == carKey) {
                    return s2;
                }
                s2 = r;
            }
        }
    }
    return ""; // No key found

    /*for (i = CHAIN_LENGTH - 1; i > 0; --i) {
        for (j = i; j < CHAIN_LENGTH; ++j) {
            r = f(md5_redux(carKey), j);
            carKey = r;
        }
        for (auto it : points) {
            if (carKey == it.second) {
                cout << "Key found " << endl;
                keys[it.first] = i;
            }
        }
    }
    return keys;*/
}

/*std::tr1::unordered_map<string, string> findS(std::tr1::unordered_map<string, int> &keys) {
    size_t i;
    string r;
    std::tr1::unordered_map<string, string> possibleKeys;
    for (auto it : keys) {
        r = it.first;
        for (i = 0; i < it.second; ++i) {
            r = f(r, i);
        }
        possibleKeys[r] = it.first;
    }
    return possibleKeys;
}*/

