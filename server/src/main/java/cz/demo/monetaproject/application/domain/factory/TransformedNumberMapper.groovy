package cz.demo.monetaproject.application.domain.factory

import cz.demo.monetaproject.application.domain.TransformedNumber
import cz.demo.monetaproject.dto.TransformedNumberCreateDto
import cz.demo.monetaproject.dto.TransformedNumberDto
import org.springframework.stereotype.Component

@Component
class TransformedNumberMapper {

    TransformedNumber toEntity(TransformedNumberCreateDto createDto) {

        TransformedNumber transformedNumber = new TransformedNumber()

        transformedNumber.inputNumber = createDto.inputNumber
        transformedNumber.transformedNumber = processNumber(createDto.inputNumber)

        transformedNumber
    }

    TransformedNumberDto toDto(TransformedNumber transformedNumber) {
        TransformedNumberDto dto = new TransformedNumberDto()

        dto.id = transformedNumber.id
        dto.inputNumber = transformedNumber.inputNumber
        dto.transformedNumber = transformedNumber.transformedNumber

        dto
    }

    private static Long processNumber(Long inputNumber) {

        char[] chars = shiftSmallDigitsRight(inputNumber.toString())

        String multiplied = multiply8and9(chars)

        String without7 = multiplied.replaceAll("7", "")

        return divideByCountOfEven(without7)
    }

    private static String multiply8and9(char[] chars) {
        StringBuilder multiplied = new StringBuilder()
        for (char c : chars) {
            switch (c) {
                case '8':
                    multiplied.append("16")
                    break
                case '9':
                    multiplied.append("18")
                    break
                default:
                    multiplied.append(c)
                    break
            }
        }
        multiplied.toString()
    }

    private static char[] shiftSmallDigitsRight(String inputNumber) {
        char[] chars = inputNumber.toCharArray()
        for (int i = chars.length - 2; i >= 0; i--) {
            int digit = Character.getNumericValue(chars[i])
            if (digit <= 3) {
                char tmp = chars[i]
                chars[i] = chars[i + 1]
                chars[i + 1] = tmp
            }
        }
        chars
    }

    private static Long divideByCountOfEven(String numberString) {
        char[] finalDigits = numberString.toCharArray()
        int evenCount = 0
        for (char c : finalDigits) {
            int d = Character.getNumericValue(c)
            if (d % 2 == 0) {
                evenCount++
            }
        }
        long finalValue = Long.parseLong(numberString)
        return (evenCount == 0) ? finalValue : (finalValue / evenCount)
    }


}
