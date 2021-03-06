(function( factory ) {
	if ( typeof define === "function" && define.amd ) {
		define([ "../datepicker" ], factory );
	} else {
		factory( jQuery.datepicker );
	}
}(function( datepicker ) {
datepicker.regional['pt-BR'] = {
	closeText: 'Fechar',
	prevText: 'Anterior',
	nextText: 'Pr\u00F3ximo',
	currentText: 'Hoje',
	monthNames: ['Janeiro','Fevereiro','Mar\u00E7o','Abril','Maio','Junho',
	'Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
	monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun',
	'Jul','Ago','Set','Out','Nov','Dez'],
	dayNames: ['Domingo','Segunda-feira','Ter\u00E7a-feira','Quarta-feira','Quinta-feira','Sexta-feira','S\u00E1bado'],
	dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','S\u00E1b'],
	dayNamesMin: ['Dom','Seg','Ter','Qua','Qui','Sex','S\u00E1b'],
	weekHeader: 'Sm',
	dateFormat: 'dd/mm/yy',
	firstDay: 0,
	isRTL: false,
	showMonthAfterYear: false,
	yearSuffix: ''};
datepicker.setDefaults(datepicker.regional['pt-BR']);

return datepicker.regional['pt-BR'];

}));