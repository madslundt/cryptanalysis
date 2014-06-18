#include <iostream>
#include <string>
#include "Rainbowtables.h"
#include "variables.h"

using namespace std;

string reduceString(string str, int bits) {
    unsigned int start;
    start = str.length() - bits;
    return str.substr(start, bits);

}

string carResponse(string str) {
    return reduceString(md5_redux(str + u), BIT_SIZE / 4);
}

string findS(std::tr1::unordered_map<string, string> &points) {
    size_t i;
    string key;
    /*std::tr1::unordered_map<string, string> suc;
    carResponse()
    */
    for (auto it : points) {
        if (it.second == s) {
            key = it.second;
            cout << "Found something - " << key << " : " << s << endl;
            for (i = 0; i < CHAIN_LENGTH; ++i) {
                if (key == s) {
                    return key;
                }
            }
        }
    }
    return "";
}

