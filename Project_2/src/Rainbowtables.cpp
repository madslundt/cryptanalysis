#include <sstream>
#include <string>
#include <iostream>
#include <cstdlib>
#include <unordered_map>
#include <stdlib.h>
#include <math.h>
#include <iostream>
#include <fstream>
#include "md5.h"
#include "variables.h"

using namespace std;

/**
 * @brief md5_redux using md5.h
 * @param str string to be hashed with md5
 * @return string hashed md5 but reduced by the BIT_SIZE
 */
string md5_redux(string str) {
    string md = md5(str + u);
    return "0x" + md.substr(0, BIT_SIZE / 4);
}

/**
 * @brief randomHex Generates a random hex of size BIT_SIZE
 * @return string hex value
 */
string randomHex() {
    size_t i;
    unsigned int length;
    int r;
    string hex;
    hex = "0x";
    length = BIT_SIZE / 4;
    for (i = 0; i < length; ++i) {
      r = rand() % 16;
      hex += hex_digits[r];
    }
    return hex;
}

/**
 * @brief f Function used when generating the chains in rainbow table. Takes two parameters to help not getting so many duplicates.
 * @param hex hex value that needs to be done the function on.
 * @param j the value that should be added to the hex.
 * @return string hex with new values
 */
string f(string hex, int j) {
    // Getting the integer value from the hex value.
    unsigned int x;
    std::stringstream ss;
    ss << std::hex << hex;
    ss >> x;

    // Added x (hex integer value) with j and mod MAX_HEX
    stringstream stream;
    stream << std::hex << (x + j) % MAX_HEX;
    return "0x" + stream.str();
}

/**
 * @brief generateRainbowtables
 * @param start_points Empty map, to be added to when generating rainbow table.
 */
void generateRainbowtables(std::tr1::unordered_map<string, string> &start_points) {
    size_t i, j;
    unsigned int count;
    string start_point, point;
    count = 0;

    // Running through the CHAINS
    for (i = 0; i < CHAINS; ++i) {
        start_point = randomHex(); // Picking a random start point
        // Checking if start point already is in the map.
        while (start_points.find(start_point) != start_points.end()) {
            cout << "OOOPS" << endl;
            if (count++ >= MAX) { // To secure no endless loop.
                return;
            }
            start_point = randomHex();
        }
        point = start_point;
        // Calulating the keys for the current chain
        for (j = 0; j < CHAIN_LENGTH; ++j) {
            point = f(md5_redux(point), j);
        }

        start_points[start_point] = point; // Storing start_point => end_point (key => value)

        if (i > 0 && i % 1000 == 0) {
            cout << i << " start points so far." << endl;
        }
    }
}

/**
 * @brief saveRainbowtable Save current start- and endpoints in csv file named SAVE_FILE
 * @param start_points Map with start- and end points.
 */
void saveRainbowtable(std::tr1::unordered_map<string, string> &start_points) {
    ofstream myfile;
    myfile.open (SAVE_FILE);
    //myfile << "Key" << "," << "Value" << endl;
    for (auto it : start_points) {
        myfile << it.first << "," << it.second << endl;
    }
    myfile.close();

}

/**
 * @brief loadRainbowtable Load csv file and importing into points.
 * @param points Empty map that gets start- and end points.
 */
void loadRainbowtable(std::tr1::unordered_map<string, string> &points) {
    ifstream myfile(SAVE_FILE);
    string start, end;
    while (getline(myfile, start, ',')) {
        getline(myfile, end);
        points[start] = end;
    }
    myfile.close();
}

