#include <iostream>
#include <string>
#include <unordered_map>
#include <vector>
#include "Rainbowtables.h"
#include "variables.h"

using namespace std;

/**
 * @brief findS
 * @param points All the start- and end points.
 * @return string with the key (empty string = no key)
 */
vector<string> findS(std::tr1::unordered_map<string, string> &points) {
    size_t i, j;
    int k;
    string r, s, possible_s, temp_s, carKey;
    vector<string> succ;
    vector<string> possible_s_values;
    s = randomHex(); // Setting a random s to be found (unknown).
    carKey = md5_redux(s); // Running the function and md5 the s.
    cout << "s\t=\t" << s << endl;
    cout << "Car key: " << carKey << endl;

    for(k = CHAIN_LENGTH; k > 0; --k){
        r = f(carKey, k); //Initial value in chain
        for(j = k + 1; j < CHAIN_LENGTH; ++j)
        {
            r = f(md5_redux(r), j); //calculating our possible successor
        }
        succ.insert(succ.end(), r); //inserting the successor into the vector of successors
    }
    cout << "All successors found" << endl;
    for(auto it : points){
        if(std::find(succ.begin(), succ.end(), it.second) != succ.end()){
            //cout << "End point found " << it.first << endl;
            possible_s = it.first;
            for(i = 0; i < CHAIN_LENGTH; ++i){
                temp_s = md5_redux(possible_s);
                if(temp_s == carKey){
                    //cout << "Possible s value: " << possible_s << endl;
                    possible_s_values.insert(possible_s_values.end(), possible_s);
                }
                possible_s = f(temp_s, i);
            }
        }
    }
    return possible_s_values;
    /*
    succ.insert(succ.begin(), carKey); // Insert into successors.

    // Calculating the next successors.
    for (i = 0; i < CHAIN_LENGTH; i++) {
        succ.insert(succ.end(), f(carKey, i));
    }
    cout << "s\t=\t" << s << endl;
    cout << "Car key: " << carKey << endl;

    r2 = succ[CHAIN_LENGTH-1];
    for(k = CHAIN_LENGTH-1; k >= 0; k--)
    {
        //cout << k << endl;

        for (auto it : points)
        {
            if(r2 == it.second)
            {
                cout << "Found something.\t" << it.first << " => " << it.second << endl;

                // Setting start point and running through the start point to see if carKey is in the chain.
                s2 = it.first;
                for (i = 0; i < CHAIN_LENGTH; ++i) {
                    r = md5_redux(s2);
                    if (r == carKey) {
                        return s2;
                    }
                    s2 = f(r, i);
                }
            }
        }
        for(j = k; j < CHAIN_LENGTH-1; j++){
        r2 = f(md5_redux(r2),k);}

    }
    return ""; // No key found

    // Going through all the start- and endpoints.
    for (auto it : points) {
        // Checks if the endpoint is in one of the successors.
        if (std::find(succ.begin(), succ.end(), it.second) != succ.end()) {

            cout << "Found something.\t" << it.first << " => " << it.second << endl;

            // Setting start point and running through the start point to see if carKey is in the chain.
            s2 = it.first;
            for (i = 0; i < CHAIN_LENGTH; ++i) {
                r = md5_redux(s2);
                if (r == carKey) {
                    return s2;
                }
                s2 = f(r, i);
            }
        }
    }
    return ""; // No key found
    */
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

