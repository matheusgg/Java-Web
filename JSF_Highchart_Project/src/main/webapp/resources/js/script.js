/** **************** Grafico de Pizza ******************** */
function renderPieChart(divId, chartTitle, chartData) {
	dataPie = $.parseJSON(chartData);
	var options = createPieChart(divId, chartTitle, dataPie);
	var chart = new Highcharts.Chart(options);
}

function createPieChart(divId, chartTitle, dataPie) {
	var options = {
		chart : {
			renderTo : divId,
			plotBackgroundColor : null,
			plotBorderWidth : null,
			plotShadow : false,
			type : 'pie'
		},
		title : {
			text : chartTitle
		},
		tooltip : {
			pointFormat : '{series.name}: <b>{point.percentage}%</b>',
			percentageDecimals : 1
		},
		plotOptions : {
			pie : {
				allowPointSelect : true,
				cursor : 'pointer',
				dataLabels : {
					enabled : true,
					color : '#000000',
					connectorColor : '#000000',
					formatter : function() {
						return '<b>' + this.point.name + '</b>: '
								+ this.percentage + ' %';
					}
				}
			}
		},
		series : [ {
			data : dataPie
		} ]
	};
	return options;
};

/** ****************** Grafico de Series ************************** */
function renderSeriesChart(divId, chartTitle, chartData, categories) {
	var options = createSeriesOption(divId, chartTitle, categories);
	options.series = $.parseJSON(chartData);
	var chart = new Highcharts.Chart(options);
}

function createSeriesOption(divId, chartTitle, categories) {
	var options = {
		colors : [ '#058DC7', '#50B432', '#ED561B', '#DDDF00', '#24CBE5',
				'#64E572', '#FF9655', '#FFF263', '#6AF9C4' ],
		chart : {
			backgroundColor : {
				linearGradient : [ 0, 0, 500, 500 ],
				stops : [ [ 0, 'rgb(255, 255, 255)' ],
						[ 1, 'rgb(240, 240, 255)' ] ]
			},
			borderWidth : 2,
			plotBackgroundColor : 'rgba(255, 255, 255, .9)',
			plotShadow : true,
			plotBorderWidth : 1,
			renderTo : divId,
			type : 'area'
		},
		title : {
			text : chartTitle,
			style : {
				color : '#000',
				font : 'bold 16px "Trebuchet MS", Verdana, sans-serif'
			}
		},
		subtitle : {
			text : 'Source: http://java-bytes.blogspot.com',
			style : {
				color : '#666666',
				font : 'bold 12px "Trebuchet MS", Verdana, sans-serif'
			}
		},
		xAxis : {
			ridLineWidth : 1,
			lineColor : '#000',
			tickColor : '#000',
			categories : $.parseJSON(categories),
			labels : {
				style : {
					color : '#000',
					font : '11px Trebuchet MS, Verdana, sans-serif'
				},
				formatter : function() {
					return this.value;
				}
			},
			title : {
				style : {
					color : '#333',
					fontWeight : 'bold',
					fontSize : '12px',
					fontFamily : 'Trebuchet MS, Verdana, sans-serif'

				}
			}
		},
		yAxis : {
			minorTickInterval : 'auto',
			lineColor : '#000',
			lineWidth : 1,
			tickWidth : 1,
			tickColor : '#000',
			title : {
				style : {
					color : '#333',
					fontWeight : 'bold',
					fontSize : '12px',
					fontFamily : 'Trebuchet MS, Verdana, sans-serif'
				},
				text : 'Heap Memory Usage'
			},
			labels : {
				formatter : function() {
					return this.value + 'MB';
				},
				style : {
					color : '#000',
					font : '11px Trebuchet MS, Verdana, sans-serif'
				}
			}
		},
		plotOptions : {
			area : {
				stacking : 'normal',
				lineColor : '#666666',
				lineWidth : 1,
				marker : {
					lineWidth : 1,
					lineColor : '#666666'
				}
			}
		},
		tooltip : {
			formatter : function() {
				return '' + this.x + ': '
						+ Highcharts.numberFormat(this.y, 0, ',') + ' MB';
			}
		},
		legend : {
			itemStyle : {
				font : '9pt Trebuchet MS, Verdana, sans-serif',
				color : 'black'

			},
			itemHoverStyle : {
				color : '#039'
			},
			itemHiddenStyle : {
				color : 'gray'
			}
		},
		labels : {
			style : {
				color : '#99b'
			}
		},
		series : []
	};

	return options;
};