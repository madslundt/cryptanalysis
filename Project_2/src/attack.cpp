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

    //loop calculating all the possible successors, starting with t, then t-1 all the way down to 0
    for(k = CHAIN_LENGTH; k > 0; --k){
        r = f(carKey, k); //Initial value in chain
        for(j = k + 1; j < CHAIN_LENGTH; ++j)
        {
            r = f(md5_redux(r), j);
        }
        succ.insert(succ.end(), r); //inserting the successor into the vector of successors
    }
    cout << "All successors found" << endl;
    //looping trough all the points to find them amongs the successors
    for(auto it : points){
        //the individual check of the point in the successors
        if(std::find(succ.begin(), succ.end(), it.second) != succ.end()){
            possible_s = it.first;
            for(i = 0; i < CHAIN_LENGTH; ++i){
                //trying to recreate the s value of the carkey using the f(s)
                temp_s = md5_redux(possible_s);
                /*
                if the md5_redux on s gives the carkey it is possible s key
                gets added to the vector of possible s values
                */
                if(temp_s == carKey){
                    possible_s_values.insert(possible_s_values.end(), possible_s);
                }
                possible_s = f(temp_s, i); //after the md5_redux is done the fi function is used on the temp_s
            }
        }
    }
    return possible_s_values;  
}

