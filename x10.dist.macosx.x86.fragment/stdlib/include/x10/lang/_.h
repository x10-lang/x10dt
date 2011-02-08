#ifndef __X10_LANG___H
#define __X10_LANG___H

#include <x10rt.h>


#define X10_LANG_OBJECT_H_NODEPS
#include <x10/lang/Object.h>
#undef X10_LANG_OBJECT_H_NODEPS
namespace x10 { namespace lang { 
class Boolean;
} } 
#include <x10/lang/Boolean.struct_h>
namespace x10 { namespace lang { 
class Byte;
} } 
#include <x10/lang/Byte.struct_h>
namespace x10 { namespace lang { 
class Short;
} } 
#include <x10/lang/Short.struct_h>
namespace x10 { namespace lang { 
class Char;
} } 
#include <x10/lang/Char.struct_h>
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
namespace x10 { namespace lang { 
class Long;
} } 
#include <x10/lang/Long.struct_h>
namespace x10 { namespace lang { 
class Float;
} } 
#include <x10/lang/Float.struct_h>
namespace x10 { namespace lang { 
class Double;
} } 
#include <x10/lang/Double.struct_h>
namespace x10 { namespace lang { 
class String;
} } 
namespace x10 { namespace lang { 
class Any;
} } 
namespace x10 { namespace lang { 
class UInt;
} } 
#include <x10/lang/UInt.struct_h>
namespace x10 { namespace lang { 
class UByte;
} } 
#include <x10/lang/UByte.struct_h>
namespace x10 { namespace lang { 
class UShort;
} } 
#include <x10/lang/UShort.struct_h>
namespace x10 { namespace lang { 
class ULong;
} } 
#include <x10/lang/ULong.struct_h>
namespace x10 { namespace lang { 
class Place;
} } 
#include <x10/lang/Place.struct_h>
namespace x10 { namespace lang { 
template<class FMGL(T)> class GlobalRef;
} } 
#include <x10/lang/GlobalRef.struct_h>
namespace x10 { namespace array { 
class Point;
} } 
namespace x10 { namespace array { 
class Region;
} } 
namespace x10 { namespace array { 
class Dist;
} } 
namespace x10 { namespace array { 
template<class FMGL(T)> class Array;
} } 
namespace x10 { namespace array { 
template<class FMGL(T)> class Array;
} } 
namespace x10 { namespace array { 
template<class FMGL(T)> class Array;
} } 
namespace x10 { namespace array { 
template<class FMGL(T)> class DistArray;
} } 
namespace x10 { namespace array { 
template<class FMGL(T)> class DistArray;
} } 
namespace x10 { namespace array { 
template<class FMGL(T)> class DistArray;
} } 
namespace x10 { namespace array { 
template<class FMGL(T)> class DistArray;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class Rail;
} } 
namespace x10 { namespace io { 
class Console;
} } 
namespace x10 { namespace lang { 

class _ : public x10::lang::Object   {
    public:
    RTT_H_DECLS_CLASS
    
    void _instance_init();
    
    virtual x10aux::ref<x10::lang::_> x10__lang_______x10__lang_____this(
      );
    void _constructor();
    
    static x10aux::ref<x10::lang::_> _make();
    
    
    // Serialization
    public: static const x10aux::serialization_id_t _serialization_id;
    
    public: virtual x10aux::serialization_id_t _get_serialization_id() {
         return _serialization_id;
    }
    
    public: virtual void _serialize_body(x10aux::serialization_buffer& buf);
    
    public: template<class __T> static x10aux::ref<__T> _deserializer(x10aux::deserialization_buffer& buf);
    
    public: void _deserialize_body(x10aux::deserialization_buffer& buf);
    
};

} } 
#endif // X10_LANG___H

namespace x10 { namespace lang { 
class _;
} } 

#ifndef X10_LANG___H_NODEPS
#define X10_LANG___H_NODEPS
#include <x10/lang/Object.h>
#include <x10/lang/Boolean.h>
#include <x10/lang/Byte.h>
#include <x10/lang/Short.h>
#include <x10/lang/Char.h>
#include <x10/lang/Int.h>
#include <x10/lang/Long.h>
#include <x10/lang/Float.h>
#include <x10/lang/Double.h>
#include <x10/lang/String.h>
#include <x10/lang/Any.h>
#include <x10/lang/UInt.h>
#include <x10/lang/UByte.h>
#include <x10/lang/UShort.h>
#include <x10/lang/ULong.h>
#include <x10/lang/Place.h>
#include <x10/lang/GlobalRef.h>
#include <x10/array/Point.h>
#include <x10/array/Region.h>
#include <x10/array/Dist.h>
#include <x10/array/Array.h>
#include <x10/array/Array.h>
#include <x10/array/Array.h>
#include <x10/array/DistArray.h>
#include <x10/array/DistArray.h>
#include <x10/array/DistArray.h>
#include <x10/array/DistArray.h>
#include <x10/lang/Rail.h>
#include <x10/io/Console.h>
#ifndef X10_LANG___H_GENERICS
#define X10_LANG___H_GENERICS
template<class __T> x10aux::ref<__T> x10::lang::_::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::lang::_> this_ = new (memset(x10aux::alloc<x10::lang::_>(), 0, sizeof(x10::lang::_))) x10::lang::_();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_LANG___H_GENERICS
#endif // __X10_LANG___H_NODEPS
