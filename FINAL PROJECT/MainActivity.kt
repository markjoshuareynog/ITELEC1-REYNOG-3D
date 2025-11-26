package com.example.unitconverter

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {

    private lateinit var spinnerConversionType: Spinner
    private lateinit var editTextInput: EditText
    private lateinit var spinnerFromUnit: Spinner
    private lateinit var spinnerToUnit: Spinner
    private lateinit var buttonConvert: Button
    private lateinit var textViewResult: TextView
    private lateinit var cardResult: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinnerConversionType = findViewById(R.id.spinnerConversionType)
        editTextInput = findViewById(R.id.editTextInput)
        spinnerFromUnit = findViewById(R.id.spinnerFromUnit)
        spinnerToUnit = findViewById(R.id.spinnerToUnit)
        buttonConvert = findViewById(R.id.buttonConvert)
        textViewResult = findViewById(R.id.textViewResult)
        cardResult = findViewById(R.id.cardResult)

        // Expanded conversion types to mimic the website
        val conversionTypes = arrayOf(
            "Length", "Area", "Volume", "Mass", "Temperature",
            "Speed", "Time", "Energy", "Pressure"
        )
        val adapter = ArrayAdapter(this, R.layout.spinner_item, conversionTypes)  // Custom layout for black text
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item)
        spinnerConversionType.adapter = adapter

        spinnerConversionType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                updateUnitSpinners(conversionTypes[position])
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        buttonConvert.setOnClickListener {
            performConversion()
        }
    }

    private fun updateUnitSpinners(type: String) {
        val units = when (type) {
            "Length" -> arrayOf("Meters", "Feet", "Inches", "Kilometers", "Miles", "Yards", "Centimeters", "Millimeters")
            "Area" -> arrayOf("Square Meters", "Square Feet", "Square Inches", "Square Kilometers", "Acres", "Hectares")
            "Volume" -> arrayOf("Liters", "Milliliters", "Cubic Meters", "Cubic Feet", "Gallons (US)", "Gallons (UK)", "Cubic Inches")
            "Mass" -> arrayOf("Kilograms", "Pounds", "Grams", "Ounces", "Tonnes", "Stones")
            "Temperature" -> arrayOf("Celsius", "Fahrenheit", "Kelvin")
            "Speed" -> arrayOf("Meters per Second", "Kilometers per Hour", "Miles per Hour", "Feet per Second", "Knots")
            "Time" -> arrayOf("Seconds", "Minutes", "Hours", "Days", "Weeks", "Years")
            "Energy" -> arrayOf("Joules", "Kilojoules", "Calories", "Kilocalories", "Watt-hours", "Kilowatt-hours")
            "Pressure" -> arrayOf("Pascals", "Kilopascals", "Bars", "Atmospheres", "Pounds per Square Inch")
            else -> arrayOf()
        }
        val adapter = ArrayAdapter(this, R.layout.spinner_item, units)  // Custom layout for black text
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item)
        spinnerFromUnit.adapter = adapter
        spinnerToUnit.adapter = adapter
    }

    private fun performConversion() {
        val input = editTextInput.text.toString().toDoubleOrNull()
        if (input == null) {
            textViewResult.text = getString(R.string.invalid_input)
            textViewResult.visibility = View.VISIBLE
            cardResult.visibility = View.VISIBLE
            return
        }

        val fromUnit = spinnerFromUnit.selectedItem?.toString()
        val toUnit = spinnerToUnit.selectedItem?.toString()
        if (fromUnit == null || toUnit == null) {
            textViewResult.text = getString(R.string.please_select_units)
            textViewResult.visibility = View.VISIBLE
            cardResult.visibility = View.VISIBLE
            return
        }

        val result = convertValue(input, fromUnit, toUnit)
        textViewResult.text = getString(R.string.result_text, result, toUnit)
        textViewResult.visibility = View.VISIBLE
        cardResult.visibility = View.VISIBLE
    }

    private fun convertValue(value: Double, from: String, to: String): Double {
        // Convert to base unit first, then to target unit (for accuracy, like the site)
        val baseValue = when (from) {
            // Length (base: meters)
            "Meters" -> value
            "Feet" -> value * 0.3048
            "Inches" -> value * 0.0254
            "Kilometers" -> value * 1000
            "Miles" -> value * 1609.34
            "Yards" -> value * 0.9144
            "Centimeters" -> value * 0.01
            "Millimeters" -> value * 0.001
            // Area (base: square meters)
            "Square Meters" -> value
            "Square Feet" -> value * 0.092903
            "Square Inches" -> value * 0.00064516
            "Square Kilometers" -> value * 1_000_000
            "Acres" -> value * 4046.86
            "Hectares" -> value * 10_000
            // Volume (base: liters)
            "Liters" -> value
            "Milliliters" -> value * 0.001
            "Cubic Meters" -> value * 1000
            "Cubic Feet" -> value * 28.3168
            "Gallons (US)" -> value * 3.78541
            "Gallons (UK)" -> value * 4.54609
            "Cubic Inches" -> value * 0.0163871
            // Mass (base: kilograms)
            "Kilograms" -> value
            "Pounds" -> value * 0.453592
            "Grams" -> value * 0.001
            "Ounces" -> value * 0.0283495
            "Tonnes" -> value * 1000
            "Stones" -> value * 6.35029
            // Temperature (handled separately)
            "Celsius" -> value
            "Fahrenheit" -> (value - 32) * 5 / 9
            "Kelvin" -> value - 273.15
            // Speed (base: meters per second)
            "Meters per Second" -> value
            "Kilometers per Hour" -> value * 0.277778
            "Miles per Hour" -> value * 0.44704
            "Feet per Second" -> value * 0.3048
            "Knots" -> value * 0.514444
            // Time (base: seconds)
            "Seconds" -> value
            "Minutes" -> value * 60
            "Hours" -> value * 3600
            "Days" -> value * 86400
            "Weeks" -> value * 604800
            "Years" -> value * 31536000
            // Energy (base: joules)
            "Joules" -> value
            "Kilojoules" -> value * 1000
            "Calories" -> value * 4.184
            "Kilocalories" -> value * 4184
            "Watt-hours" -> value * 3600
            "Kilowatt-hours" -> value * 3_600_000
            // Pressure (base: pascals)
            "Pascals" -> value
            "Kilopascals" -> value * 1000
            "Bars" -> value * 100_000
            "Atmospheres" -> value * 101_325
            "Pounds per Square Inch" -> value * 6894.76
            else -> value
        }

        return when (to) {
            // Length
            "Meters" -> baseValue
            "Feet" -> baseValue / 0.3048
            "Inches" -> baseValue / 0.0254
            "Kilometers" -> baseValue / 1000
            "Miles" -> baseValue / 1609.34
            "Yards" -> baseValue / 0.9144
            "Centimeters" -> baseValue / 0.01
            "Millimeters" -> baseValue / 0.001
            // Area
            "Square Meters" -> baseValue
            "Square Feet" -> baseValue / 0.092903
            "Square Inches" -> baseValue / 0.00064516
            "Square Kilometers" -> baseValue / 1_000_000
            "Acres" -> baseValue / 4046.86
            "Hectares" -> baseValue / 10_000
            // Volume
            "Liters" -> baseValue
            "Milliliters" -> baseValue / 0.001
            "Cubic Meters" -> baseValue / 1000
            "Cubic Feet" -> baseValue / 28.3168
            "Gallons (US)" -> baseValue / 3.78541
            "Gallons (UK)" -> baseValue / 4.54609
            "Cubic Inches" -> baseValue / 0.0163871
            // Mass
            "Kilograms" -> baseValue
            "Pounds" -> baseValue / 0.453592
            "Grams" -> baseValue / 0.001
            "Ounces" -> baseValue / 0.0283495
            "Tonnes" -> baseValue / 1000
            "Stones" -> baseValue / 6.35029
            // Temperature
            "Celsius" -> baseValue
            "Fahrenheit" -> baseValue * 9 / 5 + 32
            "Kelvin" -> baseValue + 273.15
            // Speed
            "Meters per Second" -> baseValue
            "Kilometers per Hour" -> baseValue / 0.277778
            "Miles per Hour" -> baseValue / 0.44704
            "Feet per Second" -> baseValue / 0.3048
            "Knots" -> baseValue / 0.514444
            // Time
            "Seconds" -> baseValue
            "Minutes" -> baseValue / 60
            "Hours" -> baseValue / 3600
            "Days" -> baseValue / 86400
            "Weeks" -> baseValue / 604800
            "Years" -> baseValue / 31536000
            // Energy
            "Joules" -> baseValue
            "Kilojoules" -> baseValue / 1000
            "Calories" -> baseValue / 4.184
            "Kilocalories" -> baseValue / 4184
            "Watt-hours" -> baseValue / 3600
            "Kilowatt-hours" -> baseValue / 3_600_000
            // Pressure
            "Pascals" -> baseValue
            "Kilopascals" -> baseValue / 1000
            "Bars" -> baseValue / 100_000
            "Atmospheres" -> baseValue / 101_325
            "Pounds per Square Inch" -> baseValue / 6894.76
            else -> baseValue
        }
    }
}