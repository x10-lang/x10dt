#ifndef __X10_LANG_PLACELOCALHANDLE_H
#define __X10_LANG_PLACELOCALHANDLE_H

#include <x10rt.h>


#define X10_LANG_ANY_H_NODEPS
#include <x10/lang/Any.h>
#undef X10_LANG_ANY_H_NODEPS
#define X10_LANG_ANY_H_NODEPS
#include <x10/lang/Any.h>
#undef X10_LANG_ANY_H_NODEPS
#define X10_LANG_PLACELOCALHANDLE_IMPL_H_NODEPS
#include <x10/lang/PlaceLocalHandle_Impl.h>
#undef X10_LANG_PLACELOCALHANDLE_IMPL_H_NODEPS
namespace x10 { namespace lang { 
class Object;
} } 
namespace x10 { namespace compiler { 
class Embed;
} } 
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
namespace x10 { namespace lang { 
class String;
} } 
namespace x10 { namespace array { 
class Dist;
} } 
namespace x10 { namespace lang { 
template<class FMGL(U)> class Fun_0_0;
} } 
namespace x10 { namespace lang { 
class Runtime;
} } 
namespace x10 { namespace lang { 
class Place;
} } 
#include <x10/lang/Place.struct_h>
namespace x10 { namespace lang { 
template<class FMGL(U)> class Fun_0_0;
} } 
namespace x10 { namespace lang { 
class FinishState;
} } 
namespace x10 { namespace lang { 
class Throwable;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class Iterator;
} } 
namespace x10 { namespace lang { 
class VoidFun_0_0;
} } 
namespace x10 { namespace compiler { 
class AsyncClosure;
} } 
namespace x10 { namespace lang { 
class RuntimeException;
} } 
namespace x10 { namespace compiler { 
class Finalization;
} } 
namespace x10 { namespace lang { 
template<class FMGL(U)> class Fun_0_0;
} } 
namespace x10 { namespace lang { 
template<class FMGL(U)> class Fun_0_0;
} } 
namespace x10 { namespace array { 
class PlaceGroup;
} } 
namespace x10 { namespace compiler { 
class Pragma;
} } 
namespace x10 { namespace compiler { 
class Native;
} } 
namespace x10 { namespace compiler { 
class NonEscaping;
} } 
namespace x10 { namespace lang { 
class Boolean;
} } 
#include <x10/lang/Boolean.struct_h>
namespace x10 { namespace compiler { 
class NativeClass;
} } 
#include <x10/lang/PlaceLocalHandle.struct_h>

namespace x10 { namespace lang { 

template<class FMGL(T)> class PlaceLocalHandle_methods  {
    public:
    static void _instance_init(x10::lang::PlaceLocalHandle<FMGL(T)>& this_);
    
    static void _constructor(x10::lang::PlaceLocalHandle<FMGL(T)>& this_, x10::lang::PlaceLocalHandle_Impl<FMGL(T)> id0);
    
    inline static x10::lang::PlaceLocalHandle<FMGL(T)> _make(x10::lang::PlaceLocalHandle_Impl<FMGL(T)> id0)
    {
        x10::lang::PlaceLocalHandle<FMGL(T)> this_; 
        _constructor(this_, id0);
        return this_;
    }
    
    static void _constructor(
      x10::lang::PlaceLocalHandle<FMGL(T)>& this_);
    
    inline static x10::lang::PlaceLocalHandle<FMGL(T)> _make(
             )
    {
        x10::lang::PlaceLocalHandle<FMGL(T)> this_; 
        _constructor(this_);
        return this_;
    }
    
    static FMGL(T)
      __apply(
      x10::lang::PlaceLocalHandle<FMGL(T)> this_);
    static void
      set(
      x10::lang::PlaceLocalHandle<FMGL(T)> this_, FMGL(T) newVal);
    static x10_int
      hashCode(
      x10::lang::PlaceLocalHandle<FMGL(T)> this_);
    static x10aux::ref<x10::lang::String>
      toString(
      x10::lang::PlaceLocalHandle<FMGL(T)> this_);
    static x10aux::ref<x10::lang::String>
      typeName(
      x10::lang::PlaceLocalHandle<FMGL(T)> this_);
    static x10_boolean
      equals(
      x10::lang::PlaceLocalHandle<FMGL(T)> this_, x10aux::ref<x10::lang::Any> other);
    static x10_boolean
      equals(
      x10::lang::PlaceLocalHandle<FMGL(T)> this_, x10::lang::PlaceLocalHandle<FMGL(T)> other);
    static x10_boolean
      _struct_equals(
      x10::lang::PlaceLocalHandle<FMGL(T)> this_, x10aux::ref<x10::lang::Any> other);
    static x10_boolean
      _struct_equals(
      x10::lang::PlaceLocalHandle<FMGL(T)> this_, x10::lang::PlaceLocalHandle<FMGL(T)> other);
    static x10::lang::PlaceLocalHandle<FMGL(T)>
      x10__lang__PlaceLocalHandle____x10__lang__PlaceLocalHandle__this(
      x10::lang::PlaceLocalHandle<FMGL(T)> this_);
    
};
template <> class PlaceLocalHandle_methods<void> {
    public:
    template<class FMGL(T)> static x10::lang::PlaceLocalHandle<FMGL(T)>
      make(
      x10aux::ref<x10::array::Dist> dist,
      x10aux::ref<x10::lang::Fun_0_0<FMGL(T)> > init);
    
    template<class FMGL(T)> static x10::lang::PlaceLocalHandle<FMGL(T)>
      makeFlat(
      x10aux::ref<x10::array::Dist> dist,
      x10aux::ref<x10::lang::Fun_0_0<FMGL(T)> > init);
    
    
};

} } 
#endif // X10_LANG_PLACELOCALHANDLE_H

namespace x10 { namespace lang { 
template<class FMGL(T)> class PlaceLocalHandle;
} } 

#ifndef X10_LANG_PLACELOCALHANDLE_H_NODEPS
#define X10_LANG_PLACELOCALHANDLE_H_NODEPS
#include <x10/lang/Any.h>
#include <x10/lang/Any.h>
#include <x10/lang/PlaceLocalHandle_Impl.h>
#include <x10/lang/Object.h>
#include <x10/compiler/Embed.h>
#include <x10/lang/Int.h>
#include <x10/lang/String.h>
#include <x10/array/Dist.h>
#include <x10/lang/Fun_0_0.h>
#include <x10/lang/Runtime.h>
#include <x10/lang/Place.h>
#include <x10/lang/Fun_0_0.h>
#include <x10/lang/FinishState.h>
#include <x10/lang/Throwable.h>
#include <x10/lang/Iterator.h>
#include <x10/lang/VoidFun_0_0.h>
#include <x10/compiler/AsyncClosure.h>
#include <x10/lang/RuntimeException.h>
#include <x10/compiler/Finalization.h>
#include <x10/lang/Fun_0_0.h>
#include <x10/lang/Fun_0_0.h>
#include <x10/array/PlaceGroup.h>
#include <x10/compiler/Pragma.h>
#include <x10/compiler/Native.h>
#include <x10/compiler/NonEscaping.h>
#include <x10/lang/Boolean.h>
#include <x10/compiler/NativeClass.h>
#ifndef X10_LANG_PLACELOCALHANDLE__CLOSURE__0_CLOSURE
#define X10_LANG_PLACELOCALHANDLE__CLOSURE__0_CLOSURE
#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_0.h>
template<class FMGL(T)> class x10_lang_PlaceLocalHandle__closure__0 : public x10::lang::Closure {
    public:
    
    static typename x10::lang::Fun_0_0<x10::lang::PlaceLocalHandle<FMGL(T)> >::template itable <x10_lang_PlaceLocalHandle__closure__0<FMGL(T) > > _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10::lang::PlaceLocalHandle<FMGL(T)> __apply() {
        
        //#line 65 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/PlaceLocalHandle.x10": x10.ast.X10Return_c
        return x10::lang::PlaceLocalHandle_methods<FMGL(T)>::_make();
        
    }
    
    // captured environment
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        x10_lang_PlaceLocalHandle__closure__0<FMGL(T) >* storage = x10aux::alloc<x10_lang_PlaceLocalHandle__closure__0<FMGL(T) > >();
        buf.record_reference(x10aux::ref<x10_lang_PlaceLocalHandle__closure__0<FMGL(T) > >(storage));
        x10aux::ref<x10_lang_PlaceLocalHandle__closure__0<FMGL(T) > > this_ = new (storage) x10_lang_PlaceLocalHandle__closure__0<FMGL(T) >();
        return this_;
    }
    
    x10_lang_PlaceLocalHandle__closure__0() { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_0<x10::lang::PlaceLocalHandle<FMGL(T)> > >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_0<x10::lang::PlaceLocalHandle<FMGL(T)> > >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/PlaceLocalHandle.x10:65";
    }

};

template<class FMGL(T)> typename x10::lang::Fun_0_0<x10::lang::PlaceLocalHandle<FMGL(T)> >::template itable <x10_lang_PlaceLocalHandle__closure__0<FMGL(T) > >x10_lang_PlaceLocalHandle__closure__0<FMGL(T) >::_itable(&x10::lang::Reference::equals, &x10::lang::Closure::hashCode, &x10_lang_PlaceLocalHandle__closure__0<FMGL(T) >::__apply, &x10_lang_PlaceLocalHandle__closure__0<FMGL(T) >::toString, &x10::lang::Closure::typeName);template<class FMGL(T)>
x10aux::itable_entry x10_lang_PlaceLocalHandle__closure__0<FMGL(T) >::_itables[2] = {x10aux::itable_entry(&x10aux::getRTT<x10::lang::Fun_0_0<x10::lang::PlaceLocalHandle<FMGL(T)> > >, &x10_lang_PlaceLocalHandle__closure__0<FMGL(T) >::_itable),x10aux::itable_entry(NULL, NULL)};

template<class FMGL(T)>
const x10aux::serialization_id_t x10_lang_PlaceLocalHandle__closure__0<FMGL(T) >::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(x10_lang_PlaceLocalHandle__closure__0<FMGL(T) >::template _deserialize<x10::lang::Reference>,x10aux::CLOSURE_KIND_NOT_ASYNC);

#endif // X10_LANG_PLACELOCALHANDLE__CLOSURE__0_CLOSURE
#ifndef X10_LANG_PLACELOCALHANDLE__CLOSURE__1_CLOSURE
#define X10_LANG_PLACELOCALHANDLE__CLOSURE__1_CLOSURE
#include <x10/lang/Closure.h>
#include <x10/lang/VoidFun_0_0.h>
template<class FMGL(T)> class x10_lang_PlaceLocalHandle__closure__1 : public x10::lang::Closure {
    public:
    
    static typename x10::lang::VoidFun_0_0::template itable <x10_lang_PlaceLocalHandle__closure__1<FMGL(T) > > _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    void __apply() {
        
        //#line 67 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/PlaceLocalHandle.x10": polyglot.ast.Eval_c
        x10::lang::PlaceLocalHandle_methods<FMGL(T)>::set(handle, x10::lang::Fun_0_0<FMGL(T)>::__apply(x10aux::nullCheck(init)));
    }
    
    // captured environment
    x10::lang::PlaceLocalHandle<FMGL(T)> handle;
    x10aux::ref<x10::lang::Fun_0_0<FMGL(T)> > init;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->handle);
        buf.write(this->init);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        x10_lang_PlaceLocalHandle__closure__1<FMGL(T) >* storage = x10aux::alloc<x10_lang_PlaceLocalHandle__closure__1<FMGL(T) > >();
        buf.record_reference(x10aux::ref<x10_lang_PlaceLocalHandle__closure__1<FMGL(T) > >(storage));
        x10::lang::PlaceLocalHandle<FMGL(T)> that_handle = buf.read<x10::lang::PlaceLocalHandle<FMGL(T)> >();
        x10aux::ref<x10::lang::Fun_0_0<FMGL(T)> > that_init = buf.read<x10aux::ref<x10::lang::Fun_0_0<FMGL(T)> > >();
        x10aux::ref<x10_lang_PlaceLocalHandle__closure__1<FMGL(T) > > this_ = new (storage) x10_lang_PlaceLocalHandle__closure__1<FMGL(T) >(that_handle, that_init);
        return this_;
    }
    
    x10_lang_PlaceLocalHandle__closure__1(x10::lang::PlaceLocalHandle<FMGL(T)> handle, x10aux::ref<x10::lang::Fun_0_0<FMGL(T)> > init) : handle(handle), init(init) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::VoidFun_0_0>(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::VoidFun_0_0>(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/PlaceLocalHandle.x10:67";
    }

};

template<class FMGL(T)> typename x10::lang::VoidFun_0_0::template itable <x10_lang_PlaceLocalHandle__closure__1<FMGL(T) > >x10_lang_PlaceLocalHandle__closure__1<FMGL(T) >::_itable(&x10::lang::Reference::equals, &x10::lang::Closure::hashCode, &x10_lang_PlaceLocalHandle__closure__1<FMGL(T) >::__apply, &x10_lang_PlaceLocalHandle__closure__1<FMGL(T) >::toString, &x10::lang::Closure::typeName);template<class FMGL(T)>
x10aux::itable_entry x10_lang_PlaceLocalHandle__closure__1<FMGL(T) >::_itables[2] = {x10aux::itable_entry(&x10aux::getRTT<x10::lang::VoidFun_0_0>, &x10_lang_PlaceLocalHandle__closure__1<FMGL(T) >::_itable),x10aux::itable_entry(NULL, NULL)};

template<class FMGL(T)>
const x10aux::serialization_id_t x10_lang_PlaceLocalHandle__closure__1<FMGL(T) >::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(x10_lang_PlaceLocalHandle__closure__1<FMGL(T) >::template _deserialize<x10::lang::Reference>,x10aux::CLOSURE_KIND_SIMPLE_ASYNC);

#endif // X10_LANG_PLACELOCALHANDLE__CLOSURE__1_CLOSURE
#ifndef X10_LANG_PLACELOCALHANDLE__CLOSURE__2_CLOSURE
#define X10_LANG_PLACELOCALHANDLE__CLOSURE__2_CLOSURE
#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_0.h>
template<class FMGL(T)> class x10_lang_PlaceLocalHandle__closure__2 : public x10::lang::Closure {
    public:
    
    static typename x10::lang::Fun_0_0<x10::lang::PlaceLocalHandle<FMGL(T)> >::template itable <x10_lang_PlaceLocalHandle__closure__2<FMGL(T) > > _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10::lang::PlaceLocalHandle<FMGL(T)> __apply() {
        
        //#line 86 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/PlaceLocalHandle.x10": x10.ast.X10Return_c
        return x10::lang::PlaceLocalHandle_methods<FMGL(T)>::_make();
        
    }
    
    // captured environment
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        x10_lang_PlaceLocalHandle__closure__2<FMGL(T) >* storage = x10aux::alloc<x10_lang_PlaceLocalHandle__closure__2<FMGL(T) > >();
        buf.record_reference(x10aux::ref<x10_lang_PlaceLocalHandle__closure__2<FMGL(T) > >(storage));
        x10aux::ref<x10_lang_PlaceLocalHandle__closure__2<FMGL(T) > > this_ = new (storage) x10_lang_PlaceLocalHandle__closure__2<FMGL(T) >();
        return this_;
    }
    
    x10_lang_PlaceLocalHandle__closure__2() { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_0<x10::lang::PlaceLocalHandle<FMGL(T)> > >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_0<x10::lang::PlaceLocalHandle<FMGL(T)> > >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/PlaceLocalHandle.x10:86";
    }

};

template<class FMGL(T)> typename x10::lang::Fun_0_0<x10::lang::PlaceLocalHandle<FMGL(T)> >::template itable <x10_lang_PlaceLocalHandle__closure__2<FMGL(T) > >x10_lang_PlaceLocalHandle__closure__2<FMGL(T) >::_itable(&x10::lang::Reference::equals, &x10::lang::Closure::hashCode, &x10_lang_PlaceLocalHandle__closure__2<FMGL(T) >::__apply, &x10_lang_PlaceLocalHandle__closure__2<FMGL(T) >::toString, &x10::lang::Closure::typeName);template<class FMGL(T)>
x10aux::itable_entry x10_lang_PlaceLocalHandle__closure__2<FMGL(T) >::_itables[2] = {x10aux::itable_entry(&x10aux::getRTT<x10::lang::Fun_0_0<x10::lang::PlaceLocalHandle<FMGL(T)> > >, &x10_lang_PlaceLocalHandle__closure__2<FMGL(T) >::_itable),x10aux::itable_entry(NULL, NULL)};

template<class FMGL(T)>
const x10aux::serialization_id_t x10_lang_PlaceLocalHandle__closure__2<FMGL(T) >::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(x10_lang_PlaceLocalHandle__closure__2<FMGL(T) >::template _deserialize<x10::lang::Reference>,x10aux::CLOSURE_KIND_NOT_ASYNC);

#endif // X10_LANG_PLACELOCALHANDLE__CLOSURE__2_CLOSURE
#ifndef X10_LANG_PLACELOCALHANDLE__CLOSURE__3_CLOSURE
#define X10_LANG_PLACELOCALHANDLE__CLOSURE__3_CLOSURE
#include <x10/lang/Closure.h>
#include <x10/lang/VoidFun_0_0.h>
template<class FMGL(T)> class x10_lang_PlaceLocalHandle__closure__3 : public x10::lang::Closure {
    public:
    
    static typename x10::lang::VoidFun_0_0::template itable <x10_lang_PlaceLocalHandle__closure__3<FMGL(T) > > _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    void __apply() {
        
        //#line 89 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/PlaceLocalHandle.x10": polyglot.ast.Eval_c
        x10::lang::PlaceLocalHandle_methods<FMGL(T)>::set(handle, x10::lang::Fun_0_0<FMGL(T)>::__apply(x10aux::nullCheck(init)));
    }
    
    // captured environment
    x10::lang::PlaceLocalHandle<FMGL(T)> handle;
    x10aux::ref<x10::lang::Fun_0_0<FMGL(T)> > init;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->handle);
        buf.write(this->init);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        x10_lang_PlaceLocalHandle__closure__3<FMGL(T) >* storage = x10aux::alloc<x10_lang_PlaceLocalHandle__closure__3<FMGL(T) > >();
        buf.record_reference(x10aux::ref<x10_lang_PlaceLocalHandle__closure__3<FMGL(T) > >(storage));
        x10::lang::PlaceLocalHandle<FMGL(T)> that_handle = buf.read<x10::lang::PlaceLocalHandle<FMGL(T)> >();
        x10aux::ref<x10::lang::Fun_0_0<FMGL(T)> > that_init = buf.read<x10aux::ref<x10::lang::Fun_0_0<FMGL(T)> > >();
        x10aux::ref<x10_lang_PlaceLocalHandle__closure__3<FMGL(T) > > this_ = new (storage) x10_lang_PlaceLocalHandle__closure__3<FMGL(T) >(that_handle, that_init);
        return this_;
    }
    
    x10_lang_PlaceLocalHandle__closure__3(x10::lang::PlaceLocalHandle<FMGL(T)> handle, x10aux::ref<x10::lang::Fun_0_0<FMGL(T)> > init) : handle(handle), init(init) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::VoidFun_0_0>(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::VoidFun_0_0>(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/PlaceLocalHandle.x10:89";
    }

};

template<class FMGL(T)> typename x10::lang::VoidFun_0_0::template itable <x10_lang_PlaceLocalHandle__closure__3<FMGL(T) > >x10_lang_PlaceLocalHandle__closure__3<FMGL(T) >::_itable(&x10::lang::Reference::equals, &x10::lang::Closure::hashCode, &x10_lang_PlaceLocalHandle__closure__3<FMGL(T) >::__apply, &x10_lang_PlaceLocalHandle__closure__3<FMGL(T) >::toString, &x10::lang::Closure::typeName);template<class FMGL(T)>
x10aux::itable_entry x10_lang_PlaceLocalHandle__closure__3<FMGL(T) >::_itables[2] = {x10aux::itable_entry(&x10aux::getRTT<x10::lang::VoidFun_0_0>, &x10_lang_PlaceLocalHandle__closure__3<FMGL(T) >::_itable),x10aux::itable_entry(NULL, NULL)};

template<class FMGL(T)>
const x10aux::serialization_id_t x10_lang_PlaceLocalHandle__closure__3<FMGL(T) >::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(x10_lang_PlaceLocalHandle__closure__3<FMGL(T) >::template _deserialize<x10::lang::Reference>,x10aux::CLOSURE_KIND_SIMPLE_ASYNC);

#endif // X10_LANG_PLACELOCALHANDLE__CLOSURE__3_CLOSURE
#ifndef X10_LANG_PLACELOCALHANDLE_H_GENERICS
#define X10_LANG_PLACELOCALHANDLE_H_GENERICS
#endif // X10_LANG_PLACELOCALHANDLE_H_GENERICS
#ifndef X10_LANG_PLACELOCALHANDLE_H_IMPLEMENTATION
#define X10_LANG_PLACELOCALHANDLE_H_IMPLEMENTATION
#include <x10/lang/PlaceLocalHandle.h>


namespace x10 { namespace lang { 
template<class FMGL(T)> class PlaceLocalHandle_ithunk0 : public x10::lang::PlaceLocalHandle<FMGL(T)> {
public:
    static x10::lang::Any::itable<PlaceLocalHandle_ithunk0<FMGL(T)> > itable;
    x10_boolean equals(x10aux::ref<x10::lang::Any> arg0) {
        return x10::lang::PlaceLocalHandle_methods<FMGL(T)>::equals(*this, arg0);
    }
    x10_int hashCode() {
        return x10::lang::PlaceLocalHandle_methods<FMGL(T)>::hashCode(*this);
    }
    x10aux::ref<x10::lang::String> toString() {
        return x10::lang::PlaceLocalHandle_methods<FMGL(T)>::toString(*this);
    }
    x10aux::ref<x10::lang::String> typeName() {
        return x10aux::type_name(*this);
    }
    
};
template<class FMGL(T)> x10::lang::Any::itable<PlaceLocalHandle_ithunk0<FMGL(T)> >  PlaceLocalHandle_ithunk0<FMGL(T)>::itable(&PlaceLocalHandle_ithunk0<FMGL(T)>::equals, &PlaceLocalHandle_ithunk0<FMGL(T)>::hashCode, &PlaceLocalHandle_ithunk0<FMGL(T)>::toString, &PlaceLocalHandle_ithunk0<FMGL(T)>::typeName);
template<class FMGL(T)> class PlaceLocalHandle_iboxithunk0 : public x10::lang::IBox<x10::lang::PlaceLocalHandle<FMGL(T)> > {
public:
    static x10::lang::Any::itable<PlaceLocalHandle_iboxithunk0<FMGL(T)> > itable;
    x10_boolean equals(x10aux::ref<x10::lang::Any> arg0) {
        return x10::lang::PlaceLocalHandle_methods<FMGL(T)>::equals(this->value, arg0);
    }
    x10_int hashCode() {
        return x10::lang::PlaceLocalHandle_methods<FMGL(T)>::hashCode(this->value);
    }
    x10aux::ref<x10::lang::String> toString() {
        return x10::lang::PlaceLocalHandle_methods<FMGL(T)>::toString(this->value);
    }
    x10aux::ref<x10::lang::String> typeName() {
        return x10aux::type_name(this->value);
    }
    
};
template<class FMGL(T)> x10::lang::Any::itable<PlaceLocalHandle_iboxithunk0<FMGL(T)> >  PlaceLocalHandle_iboxithunk0<FMGL(T)>::itable(&PlaceLocalHandle_iboxithunk0<FMGL(T)>::equals, &PlaceLocalHandle_iboxithunk0<FMGL(T)>::hashCode, &PlaceLocalHandle_iboxithunk0<FMGL(T)>::toString, &PlaceLocalHandle_iboxithunk0<FMGL(T)>::typeName);
} } 
template<class FMGL(T)> x10aux::itable_entry x10::lang::PlaceLocalHandle<FMGL(T)>::_itables[2] = {x10aux::itable_entry(&x10aux::getRTT<x10::lang::Any>, &PlaceLocalHandle_ithunk0<FMGL(T)>::itable), x10aux::itable_entry(NULL, (void*)x10aux::getRTT<x10::lang::PlaceLocalHandle<FMGL(T)> >())};
template<class FMGL(T)> x10aux::itable_entry x10::lang::PlaceLocalHandle<FMGL(T)>::_iboxitables[2] = {x10aux::itable_entry(&x10aux::getRTT<x10::lang::Any>, &PlaceLocalHandle_iboxithunk0<FMGL(T)>::itable), x10aux::itable_entry(NULL, (void*)x10aux::getRTT<x10::lang::PlaceLocalHandle<FMGL(T)> >())};
template<class FMGL(T)> void x10::lang::PlaceLocalHandle_methods<FMGL(T)>::_instance_init(x10::lang::PlaceLocalHandle<FMGL(T)>& this_) {
    _I_("Doing initialisation for class: x10::lang::PlaceLocalHandle<FMGL(T)>");
    
}


//#line 36 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/PlaceLocalHandle.x10": x10.ast.X10FieldDecl_c

//#line 36 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/PlaceLocalHandle.x10": x10.ast.X10ConstructorDecl_c
template<class FMGL(T)> void x10::lang::PlaceLocalHandle_methods<FMGL(T)>::_constructor(
                          x10::lang::PlaceLocalHandle<FMGL(T)>& this_, x10::lang::PlaceLocalHandle_Impl<FMGL(T)> id0)
{
    
    //#line 36 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/PlaceLocalHandle.x10": polyglot.ast.Eval_c
    this_->
      FMGL(__NATIVE_FIELD__) =
      id0;
    
}


//#line 39 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/PlaceLocalHandle.x10": x10.ast.X10ConstructorDecl_c
template<class FMGL(T)> void x10::lang::PlaceLocalHandle_methods<FMGL(T)>::_constructor(
                          x10::lang::PlaceLocalHandle<FMGL(T)>& this_)
{
    
    //#line 36 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/PlaceLocalHandle.x10": polyglot.ast.Eval_c
    this_->
      FMGL(__NATIVE_FIELD__) =
      x10::lang::PlaceLocalHandle_Impl_methods<FMGL(T)>::_make();
    
}


//#line 44 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/PlaceLocalHandle.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> FMGL(T) x10::lang::PlaceLocalHandle_methods<FMGL(T)>::__apply(
  x10::lang::PlaceLocalHandle<FMGL(T)> this_) {
    
    //#line 36 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/PlaceLocalHandle.x10": x10.ast.X10Return_c
    return x10::lang::PlaceLocalHandle_Impl_methods<FMGL(T)>::__apply(this_->
                                                                        FMGL(__NATIVE_FIELD__));
    
}

//#line 47 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/PlaceLocalHandle.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> void x10::lang::PlaceLocalHandle_methods<FMGL(T)>::set(
  x10::lang::PlaceLocalHandle<FMGL(T)> this_, FMGL(T) newVal) {
    
    //#line 36 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/PlaceLocalHandle.x10": polyglot.ast.Eval_c
    x10::lang::PlaceLocalHandle_Impl_methods<FMGL(T)>::set(this_->
                                                             FMGL(__NATIVE_FIELD__), 
      newVal);
}

//#line 49 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/PlaceLocalHandle.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10_int x10::lang::PlaceLocalHandle_methods<FMGL(T)>::hashCode(
  x10::lang::PlaceLocalHandle<FMGL(T)> this_) {
    
    //#line 36 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/PlaceLocalHandle.x10": x10.ast.X10Return_c
    return x10::lang::PlaceLocalHandle_Impl_methods<FMGL(T)>::hashCode(this_->
                                                                         FMGL(__NATIVE_FIELD__));
    
}

//#line 51 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/PlaceLocalHandle.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10aux::ref<x10::lang::String>
  x10::lang::PlaceLocalHandle_methods<FMGL(T)>::toString(
  x10::lang::PlaceLocalHandle<FMGL(T)> this_) {
    
    //#line 36 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/PlaceLocalHandle.x10": x10.ast.X10Return_c
    return x10::lang::PlaceLocalHandle_Impl_methods<FMGL(T)>::toString(this_->
                                                                         FMGL(__NATIVE_FIELD__));
    
}

//#line 64 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/PlaceLocalHandle.x10": x10.ast.X10MethodDecl_c

//#line 85 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/PlaceLocalHandle.x10": x10.ast.X10MethodDecl_c

//#line 36 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/PlaceLocalHandle.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10aux::ref<x10::lang::String>
  x10::lang::PlaceLocalHandle_methods<FMGL(T)>::typeName(
  x10::lang::PlaceLocalHandle<FMGL(T)> this_) {
    
    //#line 36 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/PlaceLocalHandle.x10": x10.ast.X10Return_c
    return x10aux::type_name(this_->FMGL(__NATIVE_FIELD__));
    
}

//#line 36 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/PlaceLocalHandle.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10_boolean x10::lang::PlaceLocalHandle_methods<FMGL(T)>::equals(
  x10::lang::PlaceLocalHandle<FMGL(T)> this_, x10aux::ref<x10::lang::Any> other) {
    
    //#line 36 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/PlaceLocalHandle.x10": x10.ast.X10If_c
    if (!(x10aux::instanceof<x10::lang::PlaceLocalHandle<FMGL(T)> >(other)))
    {
        
        //#line 36 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/PlaceLocalHandle.x10": x10.ast.X10Return_c
        return false;
        
    }
    
    //#line 36 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/PlaceLocalHandle.x10": x10.ast.X10Return_c
    return x10::lang::PlaceLocalHandle_methods<FMGL(T)>::equals(this_, 
             x10aux::class_cast<x10::lang::PlaceLocalHandle<FMGL(T)> >(other));
    
}

//#line 36 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/PlaceLocalHandle.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10_boolean x10::lang::PlaceLocalHandle_methods<FMGL(T)>::equals(
  x10::lang::PlaceLocalHandle<FMGL(T)> this_, x10::lang::PlaceLocalHandle<FMGL(T)> other) {
    
    //#line 36 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/PlaceLocalHandle.x10": x10.ast.X10Return_c
    return true;
    
}

//#line 36 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/PlaceLocalHandle.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10_boolean x10::lang::PlaceLocalHandle_methods<FMGL(T)>::_struct_equals(
  x10::lang::PlaceLocalHandle<FMGL(T)> this_, x10aux::ref<x10::lang::Any> other) {
    
    //#line 36 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/PlaceLocalHandle.x10": x10.ast.X10If_c
    if (!(x10aux::instanceof<x10::lang::PlaceLocalHandle<FMGL(T)> >(other)))
    {
        
        //#line 36 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/PlaceLocalHandle.x10": x10.ast.X10Return_c
        return false;
        
    }
    
    //#line 36 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/PlaceLocalHandle.x10": x10.ast.X10Return_c
    return x10::lang::PlaceLocalHandle_methods<FMGL(T)>::_struct_equals(this_, 
             x10aux::class_cast<x10::lang::PlaceLocalHandle<FMGL(T)> >(other));
    
}

//#line 36 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/PlaceLocalHandle.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10_boolean x10::lang::PlaceLocalHandle_methods<FMGL(T)>::_struct_equals(
  x10::lang::PlaceLocalHandle<FMGL(T)> this_, x10::lang::PlaceLocalHandle<FMGL(T)> other) {
    
    //#line 36 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/PlaceLocalHandle.x10": x10.ast.X10Return_c
    return true;
    
}

//#line 34 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/PlaceLocalHandle.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10::lang::PlaceLocalHandle<FMGL(T)>
  x10::lang::PlaceLocalHandle_methods<FMGL(T)>::x10__lang__PlaceLocalHandle____x10__lang__PlaceLocalHandle__this(
  x10::lang::PlaceLocalHandle<FMGL(T)> this_) {
    
    //#line 34 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/PlaceLocalHandle.x10": x10.ast.X10Return_c
    return this_;
    
}
template<class FMGL(T)> void x10::lang::PlaceLocalHandle<FMGL(T)>::_serialize(x10::lang::PlaceLocalHandle<FMGL(T)> this_, x10aux::serialization_buffer& buf) {
    buf.write(this_->FMGL(__NATIVE_FIELD__));
    
}

template<class FMGL(T)> void x10::lang::PlaceLocalHandle<FMGL(T)>::_deserialize_body(x10aux::deserialization_buffer& buf) {
    FMGL(__NATIVE_FIELD__) = buf.read<x10::lang::PlaceLocalHandle_Impl<FMGL(T)> >();
}


template<class FMGL(T)> x10_boolean x10::lang::PlaceLocalHandle<FMGL(T)>::equals(x10aux::ref<x10::lang::Any> that) {
    return x10::lang::PlaceLocalHandle_methods<FMGL(T)>::equals(*this, that);
}
template<class FMGL(T)> x10_boolean x10::lang::PlaceLocalHandle<FMGL(T)>::equals(x10::lang::PlaceLocalHandle<FMGL(T)> that) {
    return x10::lang::PlaceLocalHandle_methods<FMGL(T)>::equals(*this, that);
}
template<class FMGL(T)> x10_boolean x10::lang::PlaceLocalHandle<FMGL(T)>::_struct_equals(x10aux::ref<x10::lang::Any> that) {
    return x10::lang::PlaceLocalHandle_methods<FMGL(T)>::_struct_equals(*this, that);
}
template<class FMGL(T)> x10_boolean x10::lang::PlaceLocalHandle<FMGL(T)>::_struct_equals(x10::lang::PlaceLocalHandle<FMGL(T)> that) {
    return x10::lang::PlaceLocalHandle_methods<FMGL(T)>::_struct_equals(*this, that);
}
template<class FMGL(T)> x10aux::ref<x10::lang::String> x10::lang::PlaceLocalHandle<FMGL(T)>::toString() {
    return x10::lang::PlaceLocalHandle_methods<FMGL(T)>::toString(*this);
}
template<class FMGL(T)> x10_int x10::lang::PlaceLocalHandle<FMGL(T)>::hashCode() {
    return x10::lang::PlaceLocalHandle_methods<FMGL(T)>::hashCode(*this);
}
template<class FMGL(T)> x10aux::RuntimeType x10::lang::PlaceLocalHandle<FMGL(T)>::rtt;
template<class FMGL(T)> void x10::lang::PlaceLocalHandle<FMGL(T)>::_initRTT() {
    const x10aux::RuntimeType *canonical = x10aux::getRTT<x10::lang::PlaceLocalHandle<void> >();
    if (rtt.initStageOne(canonical)) return;
    const x10aux::RuntimeType* parents[2] = { x10aux::getRTT<x10::lang::Any>(), x10aux::getRTT<x10::lang::Any>()};
    const x10aux::RuntimeType* params[1] = { x10aux::getRTT<FMGL(T)>()};
    x10aux::RuntimeType::Variance variances[1] = { x10aux::RuntimeType::invariant};
    const char *baseName = "x10.lang.PlaceLocalHandle";
    rtt.initStageTwo(baseName, x10aux::RuntimeType::struct_kind, 2, parents, 1, params, variances);
}
template<class FMGL(T)> x10::lang::PlaceLocalHandle<FMGL(T)>
  x10::lang::PlaceLocalHandle_methods<void>::make(x10aux::ref<x10::array::Dist> dist,
                                                  x10aux::ref<x10::lang::Fun_0_0<FMGL(T)> > init)
{
    
    //#line 65 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/PlaceLocalHandle.x10": x10.ast.X10LocalDecl_c
    x10::lang::PlaceLocalHandle<FMGL(T)> handle =
      x10::lang::Runtime::template evalAt<x10::lang::PlaceLocalHandle<FMGL(T)> >(
        x10::lang::Place_methods::
          FMGL(FIRST_PLACE__get)(),
        x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Fun_0_0<x10::lang::PlaceLocalHandle<FMGL(T)> > > >(x10aux::ref<x10::lang::Fun_0_0<x10::lang::PlaceLocalHandle<FMGL(T)> > >(x10aux::ref<x10_lang_PlaceLocalHandle__closure__0<FMGL(T) > >(new (x10aux::alloc<x10::lang::Fun_0_0<x10::lang::PlaceLocalHandle<FMGL(T)> > >(sizeof(x10_lang_PlaceLocalHandle__closure__0<FMGL(T)>)))x10_lang_PlaceLocalHandle__closure__0<FMGL(T)>()))));
    {
        
        //#line 66 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/PlaceLocalHandle.x10": polyglot.ast.Eval_c
        x10::lang::Runtime::ensureNotInAtomic();
        
        //#line 66 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/PlaceLocalHandle.x10": x10.ast.X10LocalDecl_c
        x10aux::ref<x10::lang::FinishState> x10____var6 =
          x10::lang::Runtime::startFinish();
        {
            
            //#line 66 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/PlaceLocalHandle.x10": x10.ast.X10LocalDecl_c
            x10aux::ref<x10::lang::Throwable> throwable1968 =
              x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Throwable> >(X10_NULL);
            
            //#line 66 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/PlaceLocalHandle.x10": polyglot.ast.Try_c
            try {
                
                //#line 66 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/PlaceLocalHandle.x10": polyglot.ast.Try_c
                try {
                    {
                        
                        //#line 66 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/PlaceLocalHandle.x10": polyglot.ast.For_c
                        {
                            x10aux::ref<x10::lang::Iterator<x10::lang::Place> > p1965;
                            for (
                                 //#line 66 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/PlaceLocalHandle.x10": x10.ast.X10LocalDecl_c
                                 p1965 =
                                   x10aux::nullCheck(x10aux::nullCheck(dist)->places())->iterator();
                                 x10::lang::Iterator<x10::lang::Place>::hasNext(x10aux::nullCheck(p1965));
                                 )
                            {
                                
                                //#line 66 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/PlaceLocalHandle.x10": x10.ast.X10LocalDecl_c
                                x10::lang::Place p =
                                  x10::lang::Iterator<x10::lang::Place>::next(x10aux::nullCheck(p1965));
                                
                                //#line 67 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/PlaceLocalHandle.x10": polyglot.ast.Eval_c
                                x10::lang::Runtime::runAsync(
                                  p,
                                  x10aux::class_cast_unchecked<x10aux::ref<x10::lang::VoidFun_0_0> >(x10aux::ref<x10::lang::VoidFun_0_0>(x10aux::ref<x10_lang_PlaceLocalHandle__closure__1<FMGL(T) > >(new (x10aux::alloc<x10::lang::VoidFun_0_0>(sizeof(x10_lang_PlaceLocalHandle__closure__1<FMGL(T)>)))x10_lang_PlaceLocalHandle__closure__1<FMGL(T)>(handle, init)))));
                            }
                        }
                        
                    }
                }
                catch (x10aux::__ref& __ref__1407) {
                    x10aux::ref<x10::lang::Throwable>& __exc__ref__1407 = (x10aux::ref<x10::lang::Throwable>&)__ref__1407;
                    if (x10aux::instanceof<x10aux::ref<x10::lang::Throwable> >(__exc__ref__1407)) {
                        x10aux::ref<x10::lang::Throwable> __lowerer__var__6__ =
                          static_cast<x10aux::ref<x10::lang::Throwable> >(__exc__ref__1407);
                        {
                            
                            //#line 66 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/PlaceLocalHandle.x10": polyglot.ast.Eval_c
                            x10::lang::Runtime::pushException(
                              __lowerer__var__6__);
                            
                            //#line 66 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/PlaceLocalHandle.x10": polyglot.ast.Throw_c
                            x10aux::throwException(x10aux::nullCheck(x10::lang::RuntimeException::_make()));
                        }
                    } else
                    throw;
                }
                
                //#line 66 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/PlaceLocalHandle.x10": polyglot.ast.Eval_c
                x10::compiler::Finalization::plausibleThrow();
            }
            catch (x10aux::__ref& __ref__1408) {
                x10aux::ref<x10::lang::Throwable>& __exc__ref__1408 = (x10aux::ref<x10::lang::Throwable>&)__ref__1408;
                if (x10aux::instanceof<x10aux::ref<x10::lang::Throwable> >(__exc__ref__1408)) {
                    x10aux::ref<x10::lang::Throwable> formal1969 =
                      static_cast<x10aux::ref<x10::lang::Throwable> >(__exc__ref__1408);
                    {
                        
                        //#line 66 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/PlaceLocalHandle.x10": polyglot.ast.Eval_c
                        throwable1968 =
                          formal1969;
                    }
                } else
                throw;
            }
            {
                
                //#line 66 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/PlaceLocalHandle.x10": polyglot.ast.Eval_c
                x10::lang::Runtime::stopFinish(
                  x10____var6);
            }
            
            //#line 66 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/PlaceLocalHandle.x10": x10.ast.X10If_c
            if ((!x10aux::struct_equals(X10_NULL,
                                        throwable1968)))
            {
                
                //#line 66 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/PlaceLocalHandle.x10": x10.ast.X10If_c
                if (!(x10aux::instanceof<x10aux::ref<x10::compiler::Finalization> >(throwable1968)))
                {
                    
                    //#line 66 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/PlaceLocalHandle.x10": polyglot.ast.Throw_c
                    x10aux::throwException(x10aux::nullCheck(throwable1968));
                }
                
            }
            
        }
    }
    
    //#line 69 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/PlaceLocalHandle.x10": x10.ast.X10Return_c
    return handle;
    
}
template<class FMGL(T)>
x10::lang::PlaceLocalHandle<FMGL(T)>
  x10::lang::PlaceLocalHandle_methods<void>::makeFlat(x10aux::ref<x10::array::Dist> dist,
                                                      x10aux::ref<x10::lang::Fun_0_0<FMGL(T)> > init)
{
    
    //#line 86 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/PlaceLocalHandle.x10": x10.ast.X10LocalDecl_c
    x10::lang::PlaceLocalHandle<FMGL(T)> handle =
      x10::lang::Runtime::template evalAt<x10::lang::PlaceLocalHandle<FMGL(T)> >(
        x10::lang::Place_methods::
          FMGL(FIRST_PLACE__get)(),
        x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Fun_0_0<x10::lang::PlaceLocalHandle<FMGL(T)> > > >(x10aux::ref<x10::lang::Fun_0_0<x10::lang::PlaceLocalHandle<FMGL(T)> > >(x10aux::ref<x10_lang_PlaceLocalHandle__closure__2<FMGL(T) > >(new (x10aux::alloc<x10::lang::Fun_0_0<x10::lang::PlaceLocalHandle<FMGL(T)> > >(sizeof(x10_lang_PlaceLocalHandle__closure__2<FMGL(T)>)))x10_lang_PlaceLocalHandle__closure__2<FMGL(T)>()))));
    
    //#line 87 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/PlaceLocalHandle.x10": x10.ast.X10LocalDecl_c
    x10aux::ref<x10::array::PlaceGroup> pg =
      x10aux::nullCheck(dist)->places();
    {
        
        //#line 88 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/PlaceLocalHandle.x10": polyglot.ast.Eval_c
        x10::lang::Runtime::ensureNotInAtomic();
        
        //#line 88 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/PlaceLocalHandle.x10": x10.ast.X10LocalDecl_c
        x10aux::ref<x10::lang::FinishState> x10____var7 =
          x10::lang::Runtime::startFinish(
            ((x10_int)3));
        {
            
            //#line 88 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/PlaceLocalHandle.x10": x10.ast.X10LocalDecl_c
            x10aux::ref<x10::lang::Throwable> throwable1971 =
              x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Throwable> >(X10_NULL);
            
            //#line 88 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/PlaceLocalHandle.x10": polyglot.ast.Try_c
            try {
                
                //#line 88 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/PlaceLocalHandle.x10": polyglot.ast.Try_c
                try {
                    {
                        
                        //#line 88 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/PlaceLocalHandle.x10": polyglot.ast.For_c
                        {
                            x10aux::ref<x10::lang::Iterator<x10::lang::Place> > p1967;
                            for (
                                 //#line 88 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/PlaceLocalHandle.x10": x10.ast.X10LocalDecl_c
                                 p1967 =
                                   x10aux::nullCheck(pg)->iterator();
                                 x10::lang::Iterator<x10::lang::Place>::hasNext(x10aux::nullCheck(p1967));
                                 )
                            {
                                
                                //#line 88 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/PlaceLocalHandle.x10": x10.ast.X10LocalDecl_c
                                x10::lang::Place p =
                                  x10::lang::Iterator<x10::lang::Place>::next(x10aux::nullCheck(p1967));
                                
                                //#line 89 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/PlaceLocalHandle.x10": polyglot.ast.Eval_c
                                x10::lang::Runtime::runAsync(
                                  p,
                                  x10aux::class_cast_unchecked<x10aux::ref<x10::lang::VoidFun_0_0> >(x10aux::ref<x10::lang::VoidFun_0_0>(x10aux::ref<x10_lang_PlaceLocalHandle__closure__3<FMGL(T) > >(new (x10aux::alloc<x10::lang::VoidFun_0_0>(sizeof(x10_lang_PlaceLocalHandle__closure__3<FMGL(T)>)))x10_lang_PlaceLocalHandle__closure__3<FMGL(T)>(handle, init)))));
                            }
                        }
                        
                    }
                }
                catch (x10aux::__ref& __ref__1409) {
                    x10aux::ref<x10::lang::Throwable>& __exc__ref__1409 = (x10aux::ref<x10::lang::Throwable>&)__ref__1409;
                    if (x10aux::instanceof<x10aux::ref<x10::lang::Throwable> >(__exc__ref__1409)) {
                        x10aux::ref<x10::lang::Throwable> __lowerer__var__7__ =
                          static_cast<x10aux::ref<x10::lang::Throwable> >(__exc__ref__1409);
                        {
                            
                            //#line 88 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/PlaceLocalHandle.x10": polyglot.ast.Eval_c
                            x10::lang::Runtime::pushException(
                              __lowerer__var__7__);
                            
                            //#line 88 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/PlaceLocalHandle.x10": polyglot.ast.Throw_c
                            x10aux::throwException(x10aux::nullCheck(x10::lang::RuntimeException::_make()));
                        }
                    } else
                    throw;
                }
                
                //#line 88 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/PlaceLocalHandle.x10": polyglot.ast.Eval_c
                x10::compiler::Finalization::plausibleThrow();
            }
            catch (x10aux::__ref& __ref__1410) {
                x10aux::ref<x10::lang::Throwable>& __exc__ref__1410 = (x10aux::ref<x10::lang::Throwable>&)__ref__1410;
                if (x10aux::instanceof<x10aux::ref<x10::lang::Throwable> >(__exc__ref__1410)) {
                    x10aux::ref<x10::lang::Throwable> formal1972 =
                      static_cast<x10aux::ref<x10::lang::Throwable> >(__exc__ref__1410);
                    {
                        
                        //#line 88 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/PlaceLocalHandle.x10": polyglot.ast.Eval_c
                        throwable1971 =
                          formal1972;
                    }
                } else
                throw;
            }
            {
                
                //#line 88 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/PlaceLocalHandle.x10": polyglot.ast.Eval_c
                x10::lang::Runtime::stopFinish(
                  x10____var7);
            }
            
            //#line 88 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/PlaceLocalHandle.x10": x10.ast.X10If_c
            if ((!x10aux::struct_equals(X10_NULL,
                                        throwable1971)))
            {
                
                //#line 88 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/PlaceLocalHandle.x10": x10.ast.X10If_c
                if (!(x10aux::instanceof<x10aux::ref<x10::compiler::Finalization> >(throwable1971)))
                {
                    
                    //#line 88 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/PlaceLocalHandle.x10": polyglot.ast.Throw_c
                    x10aux::throwException(x10aux::nullCheck(throwable1971));
                }
                
            }
            
        }
    }
    
    //#line 91 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/PlaceLocalHandle.x10": x10.ast.X10Return_c
    return handle;
    
}
#endif // X10_LANG_PLACELOCALHANDLE_H_IMPLEMENTATION
#endif // __X10_LANG_PLACELOCALHANDLE_H_NODEPS
