/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.wicketstuff.objectautocomplete.example;

import org.apache.wicket.PageParameters;
import org.apache.wicket.ajax.form.AjaxFormSubmitBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.wicketstuff.objectautocomplete.ObjectAutoCompleteField;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Homepage
 */
public class HomePage extends WebPage<Void> {

	private static final long serialVersionUID = 1L;


    public HomePage() {
        final Form form = new Form("form");
        add(form);



        ObjectAutoCompleteField<Car,Integer> field = new ObjectAutoCompleteField<Car,Integer>("carAutocomplete") {
            @Override
            public Iterator<Car> getChoices(String input) {
                return searchForCar(input).iterator();
            }
        };
        form.add(field);


        final Label<Integer> label = new Label<Integer>("selectedId", field.getModel());
		label.setOutputMarkupId(true);
		form.add(label);
        field.updateOnModelChange(label);
    }

    private List<Car> searchForCar(String input) {
        List<Car> cars = getCars();
        List<Car> ret = new ArrayList<Car>();
        for (Car car : cars) {
            if (car.getName().toLowerCase().startsWith(input.toLowerCase())) {
                ret.add(car);
            }
        }
        return ret;
    }

    private List<Car> getCars() {
        return Arrays.asList(
                new Car(1,"Audi"),
                new Car(2,"BMW"),
                new Car(3,"Benz"));
    };

}