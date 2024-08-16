import pytest

from model.application.model_service import ModelService
from model.domain.repository.model_repo import IModelRepository


@pytest.fixture
def model_service_dependencies(mocker):
    model_repo_mock = mocker.Mock(spec=IModelRepository)

    return model_repo_mock


def test_custom_location_list(model_service_dependencies):
    model_repo_mock = model_service_dependencies
    model_service = ModelService(
        model_repo_mock
    )

    preferred_location = {"123123": 2}

    model_repo_mock.custom_location_list.return_value = ["123456"]

    custom_location_list = model_service.custom_location_list(
        preferred_location=preferred_location,
        page=1,
    )

    assert custom_location_list == ["123456"]
    # model_service.model_repo.custom_location_list.assert_called_once_with()
