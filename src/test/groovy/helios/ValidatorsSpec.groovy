package helios

import static helios.Validators.min
import static helios.Validators.max
import static helios.Validators.minOfList
import static helios.Validators.maxOfString
import static helios.Validators.minOfString
import static helios.Validators.maxOfList
import static helios.Validators.positive

import static helios.Helios.validate

import spock.genesis.Gen
import spock.genesis.generators.Generator

import spock.lang.Timeout
import spock.lang.Specification

import helios.CommonProperties as PROPERTY_COMMON

class ValidatorsSpec extends Specification {

    static final Integer LIMIT = 100

    void 'check Validators final class'() {
        given: 'an Validators class'
        Class<Validators> validatorsClass = Validators

        expect: 'constructor to be one'
        PROPERTY_COMMON.classHasNoPublicConstructor validatorsClass
    }

    void 'check min validator'() {
        expect: "failure for numbers below min"
        (num < min) == !isValid

        and: "success for numbers greater equal than min"
        (num >= min) == isValid

        where: 'possible values are'
        num << genZeroThenAnyInt.take(LIMIT)
        min << genZeroThenAnyInt.take(LIMIT)

        and: "validation result"
        isValid = validate("num", num, min(min)).isEmpty()
    }

    void 'check max validator'() {
        expect: 'failure for numbers greater than max'
        (num > max) == !isValid

        and: 'success for numbers less equal than max'
        (num <= max) == isValid

        where: 'possible values are'
        num << genZeroThenAnyInt.take(LIMIT)
        max << genZeroThenAnyInt.take(LIMIT)

        and: 'validation result'
        isValid = validate("num", num, max(max)).isEmpty()
    }

    void 'check positive validator'() {
        expect: "to fail for numbers less or equals zero"
        (num <= 0) == !isValid

        and: "succeed for numbers greater than zero"
        (num > 0) == isValid

        where: "possible integer values are"
        num << genZeroThenAnyInt.take(LIMIT)

        and: "validation result"
        isValid = validate("num", num, positive()).isEmpty()
    }

    void 'check min of list validator'() {
        expect: 'failure for sizes below min'
        (listSize >= min) == isValid

        and: "success for sizes greater equal than min"
        (listSize < min) == !isValid

        where: 'possible lists are'
        list << Gen.list(genZeroThenAnyInt, 1).take(LIMIT)

        and: 'the min list size restriction is'
        min  << Gen.integer(0, 2).take(LIMIT)

        and: "validation result and list size"
        isValid  = validate("list", list, minOfList(min)).isEmpty()
        listSize = list.size()
    }

    void 'check max of list validator'() {
        expect: 'failure for sizes greater than max'
        (listSize > max) == !isValid

        and: 'success for sizes less equal than max'
        (listSize <= max) == isValid

        where: 'possible lists are'
        list << Gen.list(genZeroThenAnyInt, 1).take(LIMIT)

        and: 'the max list size restriction is'
        max << Gen.integer(0,2).take(LIMIT)

        and: 'validation result and list size'
        isValid  = validate("list", list, maxOfList(max)).isEmpty()
        listSize = list.size()
    }

    void 'check min of string validator'() {
        expect: 'failure for strings shorter than min'
        (stringLength < min) == !isValid

        and: 'success for string greater equal than min'
        (stringLength >= min) == isValid

        where: 'possible strings are'
        string << Gen.string(0,1).take(LIMIT)

        and: 'the min string restriction is'
        min << Gen.integer(0,2).take(LIMIT)

        and: 'validation result and string lenght are'
        isValid      = validate("list", string, minOfString(min)).isEmpty()
        stringLength = string.length()
    }

    void 'check max of string validator'() {
        expect: 'failure for string greater than max'
        (stringLength > max) == !isValid

        and: 'success for string lower equal than'
        (stringLength <= max) == isValid

        where: 'possible strings are'
        string << Gen.string(0,1).take(LIMIT)

        and: 'the max string restriction'
        max << Gen.integer(0,2).take(LIMIT)

        and: 'validation result and string length'
        isValid = validate("string", string, maxOfString(max)).isEmpty()
        stringLength  = string.length()
    }

    Generator getGenZeroThenAnyInt() {
        return Gen.once(0).then(Gen.integer)
    }

    Generator getIntegerWithNull() {
        return Gen.once(null).then(Gen.integer)
    }
}
