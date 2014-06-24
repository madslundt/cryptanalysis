#ifndef RAINBOWTABLES_H
#define RAINBOWTABLES_H
#include <string>
#include <unordered_map>

std::string md5_redux(std::string str);
std::string randomHex();
std::string f(std::string hex, int j);
void generateRainbowtables(std::tr1::unordered_map<std::string, std::string> &start_points);
void loadRainbowtable(std::tr1::unordered_map<std::string, std::string> &points);
void saveRainbowtable(std::tr1::unordered_map<std::string, std::string> &start_points);

#endif // RAINBOWTABLES_H
