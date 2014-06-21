#include <iostream>
#include <string>
#include <tr1/unordered_map>
#include <vector>
#include "Rainbowtables.h"
#include "variables.h"

using namespace std;

string findS(std::tr1::unordered_map<string, string> &points) {
    size_t i, j;
    string r, s, carKey;
    vector<string> succ;
    s = randomHex();
    carKey = f(md5_redux(s), 0);
    succ.insert(succ.begin(), carKey);
    for (i = 0; i < CHAIN_LENGTH; ++i) {
        succ.insert(succ.end(), f(md5_redux(succ[i]), i+1));
    }
    cout << "s\t=\t" << s << endl;

    for (auto it : points) {
        if (std::find(succ.begin(), succ.end(), it.second) != succ.end())
        {
            // Element in vector.
            cout << "Found something.\t" << it.first << " => " << it.second << endl;
            s = it.first;
            for (i = 0; i < CHAIN_LENGTH; ++i) {
                r = f(md5_redux(s), i);
                if (r == carKey) {
                    return s;
                }
                s = r;
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

