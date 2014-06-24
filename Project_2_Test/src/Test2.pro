TEMPLATE = app
CONFIG += console
CONFIG -= app_bundle
CONFIG -= qt

SOURCES += main.cpp \
    md5.cpp \
    attack.cpp \
    Rainbowtables.cpp

HEADERS += \
    md5.h \
    Rainbowtables.h \
    attack.h \
    variables.h

# remove possible other optimization flags
QMAKE_CXXFLAGS_RELEASE -= -O
QMAKE_CXXFLAGS_RELEASE -= -O1
QMAKE_CXXFLAGS_RELEASE -= -O2 

# add the desired -O3 if not present
QMAKE_CXXFLAGS_RELEASE *= -O3