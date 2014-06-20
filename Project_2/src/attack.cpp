#include <iostream>
#include <string>
#include <tr1/unordered_map>
#include "Rainbowtables.h"
#include "variables.h"

using namespace std;

std::tr1::unordered_map<string, int> findEndPoints(std::tr1::unordered_map<string, string> &points) {
    size_t i, j;
    string r, s, carKey;
    std::tr1::unordered_map<string, int> keys;
    s = "s123123";//randomHex();
    carKey = md5_redux(s);
    cout << "s\t=\t" << s << endl;
    cout << "carkey\t=\t" << carKey << endl;
    for (i = CHAIN_LENGTH - 1; i > 0; --i) {
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
    return keys;
}

std::tr1::unordered_map<string, string> findS(std::tr1::unordered_map<string, int> &keys) {
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
}

