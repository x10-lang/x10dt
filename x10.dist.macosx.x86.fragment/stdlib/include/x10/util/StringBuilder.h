#ifndef __X10_UTIL_STRINGBUILDER_H
#define __X10_UTIL_STRINGBUILDER_H

#include <x10rt.h>


#define X10_LANG_OBJECT_H_NODEPS
#include <x10/lang/Object.h>
#undef X10_LANG_OBJECT_H_NODEPS
#define X10_UTIL_BUILDER_H_NODEPS
#include <x10/util/Builder.h>
#undef X10_UTIL_BUILDER_H_NODEPS
namespace x10 { namespace lang { 
class String;
} } 
namespace x10 { namespace util { 
template<class FMGL(T)> class ArrayList;
} } 
namespace x10 { namespace lang { 
class Char;
} } 
#include <x10/lang/Char.struct_h>
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
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
namespace x10 { namespace array { 
template<class FMGL(T)> class Array;
} } 
namespace x10 { namespace compiler { 
class Pinned;
} } 
namespace x10 { namespace util { 

class StringBuilder : public x10::lang::Object   {
    public:
    RTT_H_DECLS_CLASS
    
    static x10aux::itable_entry _itables[3];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    static x10::util::Builder<x10aux::ref<x10::lang::Object>, x10aux::ref<x10::lang::String> >::itable<x10::util::StringBuilder > _itable_0;
    
    static x10::lang::Any::itable<x10::util::StringBuilder > _itable_1;
    
    void _instance_init();
    
    x10aux::ref<x10::util::ArrayList<x10_char> > FMGL(buf);
    
    void _constructor();
    
    static x10aux::ref<x10::util::StringBuilder> _make();
    
    virtual x10aux::ref<x10::lang::String> toString();
    virtual x10aux::ref<x10::util::Builder<x10aux::ref<x10::lang::Object>, x10aux::ref<x10::lang::String> > >
      add(
      x10aux::ref<x10::lang::Object> o);
    virtual x10aux::ref<x10::util::StringBuilder> insert(x10_int p,
                                                         x10aux::ref<x10::lang::Object> o);
    virtual x10aux::ref<x10::util::StringBuilder> add(x10_char x);
    virtual x10aux::ref<x10::util::StringBuilder> add(x10_boolean x);
    virtual x10aux::ref<x10::util::StringBuilder> add(x10_byte x);
    virtual x10aux::ref<x10::util::StringBuilder> add(x10_short x);
    virtual x10aux::ref<x10::util::StringBuilder> add(x10_int x);
    virtual x10aux::ref<x10::util::StringBuilder> add(x10_long x);
    virtual x10aux::ref<x10::util::StringBuilder> add(x10_float x);
    virtual x10aux::ref<x10::util::StringBuilder> add(x10_double x);
    virtual x10aux::ref<x10::util::StringBuilder> add(x10aux::ref<x10::lang::String> x);
    virtual x10aux::ref<x10::util::StringBuilder> insert(x10_int p,
                                                         x10_boolean x);
    virtual x10aux::ref<x10::util::StringBuilder> insert(x10_int p,
                                                         x10_byte x);
    virtual x10aux::ref<x10::util::StringBuilder> insert(x10_int p,
                                                         x10_char x);
    virtual x10aux::ref<x10::util::StringBuilder> insert(x10_int p,
                                                         x10_short x);
    virtual x10aux::ref<x10::util::StringBuilder> insert(x10_int p,
                                                         x10_int x);
    virtual x10aux::ref<x10::util::StringBuilder> insert(x10_int p,
                                                         x10_long x);
    virtual x10aux::ref<x10::util::StringBuilder> insert(x10_int p,
                                                         x10_float x);
    virtual x10aux::ref<x10::util::StringBuilder> insert(x10_int p,
                                                         x10_double x);
    virtual x10aux::ref<x10::util::StringBuilder> insert(x10_int p,
                                                         x10aux::ref<x10::lang::String> x);
    virtual x10aux::ref<x10::util::StringBuilder> addString(x10aux::ref<x10::lang::String> s);
    virtual x10aux::ref<x10::util::StringBuilder> insertString(x10_int pos,
                                                               x10aux::ref<x10::lang::String> s);
    virtual x10_int length();
    virtual x10aux::ref<x10::lang::String> result();
    virtual x10aux::ref<x10::util::StringBuilder> x10__util__StringBuilder____x10__util__StringBuilder__this(
      );
    
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
#endif // X10_UTIL_STRINGBUILDER_H

namespace x10 { namespace util { 
class StringBuilder;
} } 

#ifndef X10_UTIL_STRINGBUILDER_H_NODEPS
#define X10_UTIL_STRINGBUILDER_H_NODEPS
#include <x10/lang/Object.h>
#include <x10/util/Builder.h>
#include <x10/lang/String.h>
#include <x10/util/ArrayList.h>
#include <x10/lang/Char.h>
#include <x10/lang/Int.h>
#include <x10/lang/Boolean.h>
#include <x10/lang/Byte.h>
#include <x10/lang/Short.h>
#include <x10/lang/Long.h>
#include <x10/lang/Float.h>
#include <x10/lang/Double.h>
#include <x10/array/Array.h>
#include <x10/compiler/Pinned.h>
#ifndef X10_UTIL_STRINGBUILDER_H_GENERICS
#define X10_UTIL_STRINGBUILDER_H_GENERICS
template<class __T> x10aux::ref<__T> x10::util::StringBuilder::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::util::StringBuilder> this_ = new (memset(x10aux::alloc<x10::util::StringBuilder>(), 0, sizeof(x10::util::StringBuilder))) x10::util::StringBuilder();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_UTIL_STRINGBUILDER_H_GENERICS
#endif // __X10_UTIL_STRINGBUILDER_H_NODEPS
