#include <string>
#include "Rainbowtables.h"
#include "variables.h"

using namespace std;

string reduceString(string str, int bits) {
    unsigned int start;
    start = str.length() - bits;
    return str.substr(start, bits);

}

string carResponse() {
    return reduceString(md5_redux(s + u), BIT_SIZE / 4);
}

string findS() {
    return s; // hehehe
}

