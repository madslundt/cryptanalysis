#include <stdlib.h>     /* srand, rand */
#include <math.h>
#include <string>
#include "Rainbowtables.h"

using namespace std;

const string s          = "S123456";
const string u          = "THISKEY";

string carResponse() {
    return md5_redux(s + u);
}

