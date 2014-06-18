#include <iostream>
#include <stdio.h>      /* printf, scanf, puts, NULL */
#include <stdlib.h>     /* srand, rand */
#include <math.h>
#include <map>
#include <tr1/unordered_map>
#include "Rainbowtables.cpp"

using namespace std;

int main()
{
    unsigned int chains, chain_length, length;
    std::tr1::unordered_map<string, string> start_points;
    std::tr1::unordered_map<string, string> end_points;
    chains = pow(2.0,18.0);
    chain_length = pow(2.0,10.0);
    generateRainbowtables(chains, chain_length, start_points, end_points);
    saveRainbowtable(start_points);
    length = start_points.size();
    cout << "Generated " << length << endl;
    for (auto it : start_points) {
        cout << it.first << " => " << it.second << endl;
    }

    return 0;
}



