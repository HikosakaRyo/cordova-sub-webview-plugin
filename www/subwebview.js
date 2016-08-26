var exec = require('cordova/exec');

var SubWebview = function() {
};

SubWebview.prototype = {

	showContents: function(success, error, options) {
	    options = setDefaults(options);
		exec(success, error, 'SubWebview', 'showContents', [options]);
	}

};

module.exports = new SubWebview();