<html><head><title>Login Validation</title></head><body>
<h1>Your Order Confirmation</h1>
<?php
$first = htmlspecialchars($_REQUEST['fname']);
$last = htmlspecialchars($_REQUEST['lname']);
$add1 = htmlspecialchars($_REQUEST['address1']);
$add2 = htmlspecialchars($_REQUEST['address2']);
$city = htmlspecialchars($_REQUEST['city']);
$state = htmlspecialchars($_REQUEST['state']);
$zip = htmlspecialchars($_REQUEST['zip']);
$address = $add1 . " " . $add2;

$dbcnx = @mysql_connect('localhost', 'johndoe', 'johndoe');

if (!$dbcnx) {
  echo( "<P>Unable to connect to the " .
        "database server at this time.</P>" );
  exit();
}

if (! @mysql_select_db("johndoe") ) {
    echo( "<p>Unable to locate the " .
          "database at this time.</p>" );
    exit();
  }

$sql = "INSERT INTO orders VALUES ( NULL, '$first', '$last', '$add1', '$add2', '$city', '$state', '$zip', 'a');";
  if (mysql_query($sql)) {
    print("<P>Your order has been added to our database.</P>");
  } else {
    print("<P>Error adding submitted order: " .
         mysql_error() . "</P>");
  }

print "You order has been successfully logged in our system.<br />";
print "<p>Your Information:</p>First name = $first <br /> Last Name = $last<br />";
print "Address = $address<br />";
print "City = $city<br />State = $state<br />Zip Code = $zip<br />";


?>
</body>
