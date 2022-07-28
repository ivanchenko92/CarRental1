package by.ivanchenko.carrental.controller.command.impl;

import by.ivanchenko.carrental.bean.car.Car;
import by.ivanchenko.carrental.controller.PageParameter;
import by.ivanchenko.carrental.controller.PageResourseManager;
import by.ivanchenko.carrental.controller.command.Command;
import by.ivanchenko.carrental.service.CarService;
import by.ivanchenko.carrental.service.ServiceException;
import by.ivanchenko.carrental.service.ServiceFactory;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static by.ivanchenko.carrental.controller.command.impl.RequestConstant.*;

public class CarInfo implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            CarService carService = ServiceFactory.getInstance().getCarService();
            List<Car> cars = carService.getCarList();
            HttpSession session = request.getSession(true);
            session.setAttribute(CARS, cars);

            return PageResourseManager.getValue(PageParameter.CAR_INFO);
        } catch (ServiceException e) {
            request.setAttribute(MESSAGE, e.getMessage());
            return PageResourseManager.getValue(PageParameter.ERROR_PAGE);
        }
    }
}