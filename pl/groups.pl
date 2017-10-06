#!/usr/bin/perl

# ========== ========== ========== ========== ========== ========== ==========
use warnings;				# Good practice
use strict;				# Good practice (force variable decalarations)
use utf8;				# utf8 pragma
use open ':std', ':encoding(UTF-8)';	# or use /usr/bin/perl -CS
use v5.10;				# Permits usage of 'say' instead of 'print'

# ========== ========== ========== ========== ========== ========== ==========
my $group;

# Prompt for username (eg. pi/linaro)
print "Which user you wish to copy groups from? ";
chomp(my $hostuser = <STDIN>);

# execute system with back ticks
# store as scalar
# check perl book to ensure this is the correct term
my $groupstring = `groups $hostuser`;

# echo scalar (for testing)
# say $groups2;

# Split string into an array
my @grouparray = split /\s+/, $groupstring;

# first three are not needed
for (my $i = 0; $i < 3; $i++) {
  my $null = shift @grouparray;
}

# output array for inspection
#foreach $group (@grouparray) {
#  say $group;
#}

print "What username would you like to modify? ";
chomp(my $username = <STDIN>);

foreach $group (@grouparray) {
	say "sudo usermod -a -G " . $group . " " . $username;
}
