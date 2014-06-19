#ifndef RAINBOWTABLES_H
#define RAINBOWTABLES_H
#include <string>
#include <map>

std::string md5_redux(std::string str);
std::string randomHex();
std::string f(std::string hex, int j);
void generateRainbowtables(std::map<std::string, std::string> &start_points,
                           std::map<std::string, std::string> &end_points);
void loadRainbowtable(std::map<std::string, std::string> &points);
void saveRainbowtable(std::map<std::string, std::string> &start_points);

#endif // RAINBOWTABLES_H
