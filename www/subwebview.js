var exec = require('cordova/exec');

var SubWebview = function() {
};

SubWebview.prototype = {

	createSubWebView: function(success, error, options) {
		exec(success, error, 'SubWebview', 'createSubWebView', [options]);
	},
	resizeSubWebView: function(success, error, options) {
		exec(success, error, 'SubWebview', 'resizeSubWebView', [options]);
	},
    /**
     * SubWebview内にコンテントを表示
     */
	showContent: function(success, error, options) {
		exec(success, error, 'SubWebview', 'showContent', [options]);
	},
	setVisible: function(success, error, visible) {
		var options = {
			"visible" : visible
		};
		exec(success, error, 'SubWebview', 'setVisible', [options]);
	}

};

module.exports = new SubWebview();