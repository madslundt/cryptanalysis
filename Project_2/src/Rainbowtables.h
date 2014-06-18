#ifndef RAINBOWTABLES_H
#define RAINBOWTABLES_H
#include <string>
#include <tr1/unordered_map>

void loadRainbowtable(std::tr1::unordered_map<std::string, std::string> &points);
void saveRainbowtable(std::tr1::unordered_map<std::string, std::string> &start_points);
std::string md5_redux(std::string str);
std::string randomHex();
std::string f(std::string hex, int j);
void generateRainbowtables(unsigned int chains, unsigned int chain_length,
                           std::tr1::unordered_map<std::string, std::string> &start_points,
                           std::tr1::unordered_map<std::string, std::string> &end_points);


#endif // RAINBOWTABLES_H
