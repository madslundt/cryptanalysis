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
    s = randomHex();
    carKey = md5_redux(s);
    for (i = CHAIN_LENGTH - 1; i > 0; --i) {
        for (j = i; j < CHAIN_LENGTH; ++j) {
            r = f(md5_redux(carKey), j);
            carKey = r;
        }
        for (auto it : points) {
            if (carKey == it.second) {
                cout << "Key found" << endl;
                keys[it.first] = i;
            }
        }
    }
    return keys;
}

void findS(std::tr1::unordered_map<string, int> &keys) {
    size_t i;
    string r;
    for (auto it : keys) {
        r = it.first;
        for (i = 0; i < it.second; ++i) {
            r = f(r, i);
        }
        cout << "For Start point: " << it.first << " and column " << it.second << " the key might be:\t" << r <<  endl;
    }
}

