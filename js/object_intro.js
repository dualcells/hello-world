const jonas = {
    firstName: 'Jonas',
    lastName: 'Schmedtmann',
    age: 2037 - 1991,
    job: 'Teacher',
    friends: ['Michael', 'Peter', 'Steven']
}
console.log(jonas);

console.log(jonas.lastName);
console.log(jonas['lastName']);

const nameKey = 'Name';

console.log(jonas['first' + nameKey]);
console.log(jonas['last' + nameKey]);

// console.log(jonas.'last' + nameKey);

const interestedIn = prompt('What do you want to know about Jonas? Choose between firstName, lastName, age, job, and friends.');
console.log(interestedIn);

if ( jonas[interestedIn] ) {
    console.log(jonas[interestedIn]);
} else {
    console.log('Wrong request! Choose between firstName, lastName, age, job, and friends.');
}

// Challenge
// "Jonas has 3 friends, and his best friend is called Michael"
var _output = jonas.firstName 
    + ' has ' + jonas.friends.length 
    + ' friends, and his best friend is called '
    + jonas.friends[0];

console.log(_output);

console.log(`${jonas.firstName} has ${jonas.friends.length} friends, and his best friend is called ${jonas.friends[0]}`);