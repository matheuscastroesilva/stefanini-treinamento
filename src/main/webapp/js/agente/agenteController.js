App.controller('AgenteCtrl', function($scope, AgenteService, $route,
		$routeParams, $location, EnvioService) {
	$scope.agente = [];
	$scope.agenteEditar = {};

	$scope.notFound = false;
	AgenteService.list().then(function(data) {
		$scope.agente = data.data;
		if (data.data.length == 0) {
			$scope.notFound = true;
		}
	}, function(data) {
		console.log("data", data);
	});

	$scope.cadastrar = function(agente) {
		var data = {
			nome : agente.nome,
			tempoServico : agente.tempoServico,
			dataContratacao : agente.dataContratacao
		};

		AgenteService.create(data).then(function(data) {
			$location.path('/');
		});
	}

	$scope.agenteEditar = EnvioService.getParametro();

	$scope.deletar = function(id) {
		AgenteService.remove(id).then(function(data) {
			console.log(data);
			$route.reload();
		});
	}

	$scope.editar = function(item) {
		$scope.agenteEditar = {};
		EnvioService.addParametro(item);
		$location.path('/editarAgente');
	}

	$scope.atualizar = function(item) {
		AgenteService.update(item, item.id).then(function(data) {
			$location.path('/');
		});
	}

});