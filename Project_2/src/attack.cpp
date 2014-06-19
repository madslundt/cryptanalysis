#include <iostream>
#include <string>
#include <map>
#include "Rainbowtables.h"
#include "variables.h"

using namespace std;

string lowestBits(string str, int bits) {
    unsigned int start;
    start = str.length() - bits;
    return "0x" + str.substr(start, bits);

}

string generateS() {
    return randomHex();
}

string carKeyAnswer(string str) {
    return lowestBits(md5_redux(str + u), BIT_SIZE / 4);
}

std::map<string, int> findEndPoints(std::map<string, string> &points) {
    size_t i, j;
    string r, s, carKey;
    std::map<string, int> keys;
    s = generateS();
    carKey = carKeyAnswer(s);
    for (i = CHAIN_LENGTH - 1; i > 0; --i) {
        for (j = i; j < CHAIN_LENGTH; ++j) {
            r = f(carKeyAnswer(carKey), j);
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

void findS(std::map<string, int> &keys) {
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

