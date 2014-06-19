#ifndef ATTACK_H
#define ATTACK_H
#include <string>
#include <tr1/unordered_map>

std::tr1::unordered_map<std::string, int> findEndPoints(std::tr1::unordered_map<std::string, std::string> &points);
void findS(std::tr1::unordered_map<std::string, int> &keys);

#endif // ATTACK_H
