#ifndef ATTACK_H
#define ATTACK_H
#include <string>
#include <map>

std::string lowestBits(std::string str, int bits);
std::string carKeyAnswer();
std::map<std::string, int> findEndPoints(std::map<std::string, std::string> &points);
void findS(std::map<std::string, int> &keys);

#endif // ATTACK_H
