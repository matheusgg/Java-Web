$(document).ready(function() {
	// Setting locale for moment
	moment.locale($('#calendar').data('lang'));

	// Create full calendar
	$('#calendar').fullCalendar({

		// Sets header options
		header : {
			right : 'today prev,next',
			left : 'title'
		},

		// Sets actual date
		now : moment(),

		// Limits events to three
		eventLimit : {
			'default' : 4
		},

		events : $('#calendar').data('source'),

		// Executed when a event is clicked
		eventClick : function(calEvent, jsEvent, view) {
			var currentPage = $('#calendar').data('location');

			if (currentPage != null && currentPage === 'dashboard') {
				prepareEventDetails({
					idEvento : calEvent.id
				});

			} else {
				selecionaEvento({
					idEvento : calEvent.id
				});
			}
		}
	});
});

function updateCalendar() {
	$('#calendar').fullCalendar('refetchEvents');
}