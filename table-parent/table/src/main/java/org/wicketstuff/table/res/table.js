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
function changeStyleOnOnMouseOver(markupId, mouseOverClass) {
	var element = document.getElementById(markupId);
	element.onselectstart = function() {
		return window.event.srcElement.tagName.toLowerCase() == "input";
	} // ie
	element.onmousedown = function(e) {
		return e != null && e.target.tagName.toLowerCase() == "input";
	} // mozilla
	element.originalClass = element.className;
	element.onmouseover = function(e) {
		element.className = mouseOverClass;
	};
	element.onmouseout = function(e) {
		element.className = element.originalClass;
	};
}
function turnTotalizer(compId) {
	var comp = Wicket.$(compId);
	var trParent = comp.parentNode;
	while ('TR' != trParent.tagName.toUpperCase()) {
		trParent = trParent.parentNode;
	}
	var updateValue = function(){
		comp.innerHTML = null;
		comp.innerHTML = calcChildrenTotal(trParent.firstChild);
	}
	updateValue();
	var addHandler = function(children) {
		if (children.tagName && children.tagName.toUpperCase() == 'INPUT') {
			children.onchange = function(){
				updateValue();
			};
		}
	};
	travel(this, trParent, addHandler);
}
function calcChildrenTotal(parent){
	var total = 0;
	var sum = function(children){
		try{
			if(!isNaN(parseFloat(children.value)))
				total = total + parseFloat(children.value);
		}catch(e){
			//it was just a try
		}
		try{
			if(!isNaN(parseFloat(children.innerHTML)))
				total = total + parseFloat(children.innerHTML);
		}catch(e){
			//it was just a try
		}
	}
	travel(this, parent, sum);
	return total;
}
function travel(caller, n, f) {
	if (n)
		f.call(caller,n, n);
	if (n.firstChild)
		travel(caller,n.firstChild, f)
	if (n.nextSibling)
		travel(caller,n.nextSibling, f)
}