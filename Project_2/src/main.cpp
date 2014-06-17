#include <iostream>
#include "md5.h"
#include <string>

using namespace std;

string test(string str) {
    string md = md5(str);
    return md;
}

int main()
{
    cout << "md5 of 'grape': " << test("grape") << endl;
    return 0;
}



