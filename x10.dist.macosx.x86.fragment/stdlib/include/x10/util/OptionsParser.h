#ifndef __X10_UTIL_OPTIONSPARSER_H
#define __X10_UTIL_OPTIONSPARSER_H

#include <x10rt.h>


#define X10_LANG_OBJECT_H_NODEPS
#include <x10/lang/Object.h>
#undef X10_LANG_OBJECT_H_NODEPS
namespace x10 { namespace util { 
template<class FMGL(K), class FMGL(V)> class HashMap;
} } 
namespace x10 { namespace lang { 
class String;
} } 
namespace x10 { namespace util { 
template<class FMGL(K), class FMGL(V)> class HashMap;
} } 
namespace x10 { namespace lang { 
class Boolean;
} } 
#include <x10/lang/Boolean.struct_h>
namespace x10 { namespace util { 
template<class FMGL(T)> class GrowableRail;
} } 
namespace x10 { namespace array { 
template<class FMGL(T)> class Array;
} } 
namespace x10 { namespace array { 
template<class FMGL(T)> class Array;
} } 
namespace x10 { namespace util { 
class Option;
} } 
#include <x10/util/Option.struct_h>
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
namespace x10 { namespace lang { 
template<class FMGL(T)> class Iterator;
} } 
namespace x10 { namespace util { 
class OptionsParser__Err;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class Rail;
} } 
namespace x10 { namespace lang { 
class Byte;
} } 
#include <x10/lang/Byte.struct_h>
namespace x10 { namespace lang { 
class NumberFormatException;
} } 
namespace x10 { namespace lang { 
class Short;
} } 
#include <x10/lang/Short.struct_h>
namespace x10 { namespace lang { 
class Long;
} } 
#include <x10/lang/Long.struct_h>
namespace x10 { namespace lang { 
class Double;
} } 
#include <x10/lang/Double.struct_h>
namespace x10 { namespace lang { 
class Float;
} } 
#include <x10/lang/Float.struct_h>
namespace x10 { namespace util { 

class OptionsParser : public x10::lang::Object   {
    public:
    RTT_H_DECLS_CLASS
    
    void _instance_init();
    
    x10aux::ref<x10::util::HashMap<x10aux::ref<x10::lang::String>, x10aux::ref<x10::lang::String> > >
      FMGL(map);
    
    x10aux::ref<x10::util::HashMap<x10aux::ref<x10::lang::String>, x10_boolean> >
      FMGL(set);
    
    x10aux::ref<x10::util::GrowableRail<x10aux::ref<x10::lang::String> > >
      FMGL(filteredArgs);
    
    void _constructor(x10aux::ref<x10::array::Array<x10aux::ref<x10::lang::String> > > args,
                      x10aux::ref<x10::array::Array<x10::util::Option> > flags,
                      x10aux::ref<x10::array::Array<x10::util::Option> > specs);
    
    static x10aux::ref<x10::util::OptionsParser> _make(
             x10aux::ref<x10::array::Array<x10aux::ref<x10::lang::String> > > args,
             x10aux::ref<x10::array::Array<x10::util::Option> > flags,
             x10aux::ref<x10::array::Array<x10::util::Option> > specs);
    
    virtual x10aux::ref<x10::lang::Rail<x10aux::ref<x10::lang::String> > >
      filteredArgs(
      );
    virtual x10_boolean __apply(x10aux::ref<x10::lang::String> key);
    virtual x10aux::ref<x10::lang::String>
      __apply(
      x10aux::ref<x10::lang::String> key,
      x10aux::ref<x10::lang::String> d);
    virtual x10_byte __apply(x10aux::ref<x10::lang::String> key,
                             x10_byte d);
    virtual x10_short __apply(x10aux::ref<x10::lang::String> key,
                              x10_short d);
    virtual x10_int __apply(x10aux::ref<x10::lang::String> key,
                            x10_int d);
    virtual x10_long __apply(x10aux::ref<x10::lang::String> key,
                             x10_long d);
    virtual x10_double __apply(x10aux::ref<x10::lang::String> key,
                               x10_double d);
    virtual x10_float __apply(x10aux::ref<x10::lang::String> key,
                              x10_float d);
    virtual x10aux::ref<x10::util::OptionsParser>
      x10__util__OptionsParser____x10__util__OptionsParser__this(
      );
    
    // Serialization
    public: static const x10aux::serialization_id_t _serialization_id;
    
    public: x10aux::serialization_id_t _get_serialization_id() {
         return _serialization_id;
    }
    
    public: virtual void _serialize_body(x10aux::serialization_buffer& buf);
    
    public: template<class __T> static x10aux::ref<__T> _deserializer(x10aux::deserialization_buffer& buf);
    
    public: void _deserialize_body(x10aux::deserialization_buffer& buf);
    
};

} } 
#endif // X10_UTIL_OPTIONSPARSER_H

namespace x10 { namespace util { 
class OptionsParser;
} } 

#ifndef X10_UTIL_OPTIONSPARSER_H_NODEPS
#define X10_UTIL_OPTIONSPARSER_H_NODEPS
#include <x10/lang/Object.h>
#include <x10/util/HashMap.h>
#include <x10/lang/String.h>
#include <x10/util/HashMap.h>
#include <x10/lang/Boolean.h>
#include <x10/util/GrowableRail.h>
#include <x10/array/Array.h>
#include <x10/array/Array.h>
#include <x10/util/Option.h>
#include <x10/lang/Int.h>
#include <x10/lang/Iterator.h>
#include <x10/util/OptionsParser__Err.h>
#include <x10/lang/Rail.h>
#include <x10/lang/Byte.h>
#include <x10/lang/NumberFormatException.h>
#include <x10/lang/Short.h>
#include <x10/lang/Long.h>
#include <x10/lang/Double.h>
#include <x10/lang/Float.h>
#ifndef X10_UTIL_OPTIONSPARSER_H_GENERICS
#define X10_UTIL_OPTIONSPARSER_H_GENERICS
template<class __T> x10aux::ref<__T> x10::util::OptionsParser::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::util::OptionsParser> this_ = new (memset(x10aux::alloc<x10::util::OptionsParser>(), 0, sizeof(x10::util::OptionsParser))) x10::util::OptionsParser();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_UTIL_OPTIONSPARSER_H_GENERICS
#endif // __X10_UTIL_OPTIONSPARSER_H_NODEPS
