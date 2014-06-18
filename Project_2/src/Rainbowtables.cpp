#include <sstream>
#include <string>
#include <iostream>
#include <tr1/unordered_map>
#include <math.h>
#include <iostream>
#include <fstream>
#include "md5.h"

using namespace std;

const string hex_digits = "0123456789ABCDEF";
const int BIT_SIZE = 28;
const unsigned int MAX = 1000;
const char* SAVE_FILE = "rainbowtable.csv";

string md5_redux(string str) {
    string md = md5(str);
    return md;
}

string randomHex() {
    size_t i;
    unsigned int length;
    string hex;
    hex = "0x";
    length = BIT_SIZE / 4;
    for (i = 0; i < length; ++i) {
      hex += hex_digits[ ( rand() % 16 ) ];
    }
    return hex;
}

string f(string hex, int j) {
    unsigned int x;
    std::stringstream ss;
    ss << std::hex << hex;
    ss >> x;

    stringstream stream;
    stream << std::hex << (x + j) % (int) pow(2.0, (double) BIT_SIZE);
    return "0x" + stream.str();
}

void generateRainbowtables(unsigned int chains, unsigned int chain_length,
                           std::tr1::unordered_map<string, string> &start_points,
                           std::tr1::unordered_map<string, string> &end_points) {
    size_t i, j;
    unsigned int count;
    string start_point, point;
    count = 0;
    for (i = 0; i < chains; ++i) {
        start_point = randomHex();
        while (start_points.find(start_point) != start_points.end()) {
            cout << "OOOPS" << endl;
            if (count++ <= MAX) { // To secure no endless loop.
                return;
            }
            start_point = randomHex();
        }
        point = start_point;
        for (j = 0; j < chain_length; ++j) {
            point = f(point, j);
        }

        start_points[start_point] = point;
        end_points[point] = start_point;

        if (i > 0 && i % 1000 == 0) {
            cout << i << " start points so far." << endl;
            break;
        }
    }
}

void saveRainbowtable(std::tr1::unordered_map<string, string> &start_points) {
    ofstream myfile;
    myfile.open (SAVE_FILE);
    myfile << "Key" << "," << "Value" << endl;
    for (auto it : start_points) {
        myfile << it.first << "," << it.second;
    }
    myfile.close();

}

void loadRainbowtable(std::tr1::unordered_map<string, string> &points) {
    ifstream myfile(SAVE_FILE);
    string start, end;
    while (getline(myfile, start, ',')) {
        getline(myfile, end);
        points[start] = end;
    }
    myfile.close();
}

